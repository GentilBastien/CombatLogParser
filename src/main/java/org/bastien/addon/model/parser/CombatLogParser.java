package org.bastien.addon.model.parser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bastien.addon.model.CombatLog;
import org.bastien.addon.model.constant.EffectType;
import org.bastien.addon.model.constant.EnergyType;
import org.bastien.addon.model.constant.Event;
import org.bastien.addon.model.constant.Location;
import org.bastien.addon.model.entities.Ability;
import org.bastien.addon.model.entities.EffectBatch;
import org.bastien.addon.model.entities.Player;
import org.bastien.addon.model.parser.impl.*;

import java.time.LocalTime;

@RequiredArgsConstructor
public class CombatLogParser implements Parser<CombatLog> {
    @Getter
    private static final CombatLogParser instance = new CombatLogParser();
    boolean isPvPArea = false;

    @Override
    public CombatLog parse(String rawLine) throws RuntimeException {
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
            this.isPvPArea = isLogInPvPLocation(effect);
        }

        Object content = null;
        if (sources.length == 6) {
            if (event != null) {
                content = buildEventContent(event, sources[5], rawLine);
            }
            if (effect != null) {
                switch (effect.getAction()) {
                    case APPLY_EFFECT -> content = buildEffectTypeContent(effect.getType(), sources[5], rawLine);
                    case MODIFY_CHARGES -> content = ParserFactory.getInstance(ChargesParser.class).parse(sources[5]);
                    case SPEND, RESTORE ->
                            content = buildEnergyTypeContent(effect.getEnergyType(), sources[5], rawLine);
                }
            }
        }
        return new CombatLog(isPvPArea, timestamp, source, target, ability, event, effect, content, rawLine);
    }

    private boolean isLogInPvPLocation(EffectBatch effect) {
        return effect.getLocation() != Location.NO_PvP_LOCATION;
    }

    private Player detectPlayerType(String source, String rawLine) {
        try {
            if (!source.contains("@")) return ParserFactory.getInstance(MobParser.class).parse(source);
            return source.contains("{") ? ParserFactory.getInstance(PetParser.class).parse(
                    source) : ParserFactory.getInstance(PlayerParser.class).parse(source);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage() + " This source has an incorrect player -> " + rawLine);
        }
    }

    private Object buildEventContent(Event event, String source, String rawLine) {
        switch (event) {
            case MODIFY_THREAT, TAUNT -> {
                return ParserFactory.getInstance(ThreatNumberParser.class).parse(source);
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
            default -> throw new RuntimeException("This effectAction types don't have content -> " + rawLine);
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
