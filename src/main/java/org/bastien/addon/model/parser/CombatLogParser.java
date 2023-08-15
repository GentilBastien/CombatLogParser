package org.bastien.addon.model.parser;

import lombok.RequiredArgsConstructor;
import org.bastien.addon.model.CombatLog;
import org.bastien.addon.model.constant.EffectType;
import org.bastien.addon.model.constant.EnergyType;
import org.bastien.addon.model.constant.Event;
import org.bastien.addon.model.entities.Ability;
import org.bastien.addon.model.entities.EffectBatch;
import org.bastien.addon.model.entities.Player;
import org.bastien.addon.model.parser.impl.*;

import java.time.LocalTime;

@RequiredArgsConstructor
public class CombatLogParser implements Parser<CombatLog> {
    private static final CombatLogParser instance = new CombatLogParser();

    public static CombatLogParser getInstance() {
        return instance;
    }

    @Override
    public CombatLog parse(String rawLine) {
        String[] sources = rawLine.split("(?<=])\\s(?=[\\[(<])");
        LocalTime timestamp = ParserFactory.getInstance(TimestampParser.class).parse(sources[0]);
        Player source = detectPlayerType(sources[1], rawLine);
        Player target = sources[2].equals("[=]") ? source : detectPlayerType(sources[2], rawLine);
        Ability ability = ParserFactory.getInstance(AbilityParser.class).parse(sources[3]);
        Event event = null;
        EffectBatch effect = null;
        if (sources[4].contains("836045448945472")) {
            event = ParserFactory.getInstance(EventParser.class).parse(sources[4]);
        } else {
            effect = ParserFactory.getInstance(EffectParser.class).parse(sources[4]);
        }

        Object content = null;
        if (sources.length == 6) {
            if (event != null) {
                content = buildEventContent(event, sources[5], rawLine);
            }
            if (effect != null) {
                switch (effect.getEffect()) {
                    case APPLY_EFFECT -> content = buildEffectTypeContent(effect.getEffectType().orElseThrow(), sources[5], rawLine);
                    case MODIFY_CHARGES -> content = ParserFactory.getInstance(ChargesParser.class).parse(sources[5]);
                    case SPEND, RESTORE -> content = buildEnergyTypeContent(effect.getEnergyType().orElseThrow(), sources[5], rawLine);
                }
            }
        }
        return CombatLog.builder().timestamp(timestamp).source(source).target(target).ability(ability).event(event).effect(effect).value(content).rawLog(rawLine).build();
    }

    private Player detectPlayerType(String source, String rawLine) {
        try {
            if (!source.contains("@"))
                return ParserFactory.getInstance(MobParser.class).parse(source);
            return source.contains("{") ?
                    ParserFactory.getInstance(PetParser.class).parse(source) :
                    ParserFactory.getInstance(PlayerParser.class).parse(source);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage() + " This source has an incorrect player -> " + rawLine);
        }
    }

    private Object buildEventContent(Event event, String source, String rawLine) {
        switch (event) {
            case MODIFY_THREAT, TAUNT -> {
                return ParserFactory.getInstance(EncochedNumberParser.class).parse(source);
            }
            case FALLING_DAMAGE, SET_LEVEL -> {
                return ParserFactory.getInstance(ParenthesisNumberParser.class).parse(source);
            }
            default -> throw new RuntimeException("This events don't have content -> " + rawLine);
        }
    }

    private Object buildEffectTypeContent(EffectType effectType, String source, String rawLine) {
        switch (effectType) {
            case HEAL -> {
                return ParserFactory.getInstance(HealParser.class).parse(source);
            }
            case DAMAGE -> {
                return ParserFactory.getInstance(DamageParser.class).parse(source);
            }
            case BUFF -> {
                return ParserFactory.getInstance(ChargesParser.class).parse(source);
            }
            default -> throw new RuntimeException("This effect types don't have content -> " + rawLine);
        }
    }

    private Object buildEnergyTypeContent(EnergyType energyType, String source, String rawLine) {
        switch (energyType) {
            case FORCE, ENERGY, FOCUS_POINT, RAGE_POINT, HEALTH_POINT -> {
                return ParserFactory.getInstance(ParenthesisNumberParser.class).parse(source);
            }
            default -> throw new RuntimeException("This energy type don't have content -> " + rawLine);
        }
    }
}
