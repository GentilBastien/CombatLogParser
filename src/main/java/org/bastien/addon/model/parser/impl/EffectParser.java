package org.bastien.addon.model.parser.impl;

import org.bastien.addon.model.constant.Effect;
import org.bastien.addon.model.constant.EffectType;
import org.bastien.addon.model.constant.EnergyType;
import org.bastien.addon.model.entities.Ability;
import org.bastien.addon.model.entities.EffectBatch;
import org.bastien.addon.model.parser.ParserFactory;

import java.util.regex.Matcher;

public class EffectParser extends RegExpParser<EffectBatch> {

    private final DisciplineParser disciplineParser;

    public EffectParser() {
        this.disciplineParser = ParserFactory.getInstance(DisciplineParser.class);
    }

    public static void main(String[] args) {
        EffectParser eventParser = new EffectParser();
        System.out.println(eventParser.parse("[Spend {836045448945473}: energy {836045448938503}]"));
        System.out.println(eventParser.parse("[ApplyEffect {836045448945477}: Damage {836045448945501}]"));
        System.out.println(eventParser.parse("[RemoveEffect {836045448945478}: Immobilized (Physical) {807750204391700}]"));
        System.out.println(eventParser.parse("[ModifyCharges {836045448953666}: Medals Earned This Match {832699669413888}]"));
        System.out.println(eventParser.parse("[ApplyEffect {836045448945477}: Heal {836045448945500}]"));
        System.out.println(eventParser.parse("[ApplyEffect {836045448945477}: Impaired (Mental) {2211062048883004}]"));
        System.out.println(eventParser.parse("[AreaEntered {836045448953664}: Rishi {833571547775718}]"));
        System.out.println(eventParser.parse("[RemoveEffect {836045448945478}:  {4434540848218112}]"));
        System.out.println(eventParser.parse("[DisciplineChanged {836045448953665}: Powertech {16141007401395916385}/Pyrotech {2031339142381602}]"));
        System.out.println(eventParser.parse("[ApplyEffect {836045448945477}: Accuracy Reduced [Tech] {2268666150256900}]"));
    }

    @Override
    public EffectBatch parse(String source) {
        if (source.equals("[]"))
            return null;
        final Matcher matcher = pattern.matcher(source);
        if (!matcher.matches())
            throw new RuntimeException("Effect parser could not parse this source " + source);
        long effectId = Long.parseLong(matcher.group(2));
        Effect effect = Effect.find(effectId);

        if (effect == Effect.DISCIPLINE_CHANGED)
            return disciplineParser.parse(source);

        String name = matcher.group(3);
        long secondId = Long.parseLong(matcher.group(4));

        switch (effect) {
            case APPLY_EFFECT, REMOVE_EFFECT, MODIFY_CHARGES, AREA_ENTERED -> {
                EffectType effectType = EffectType.find(secondId);
                if (effectType == EffectType.BUFF) {
                    Ability ability = new Ability(secondId, name, true);
                    return new EffectBatch(effect, effectType, ability);
                }
                return new EffectBatch(effect, effectType);
            }
            case SPEND, RESTORE -> {
                EnergyType energyType = EnergyType.find(secondId);
                return new EffectBatch(effect, energyType);
            }
        }
        throw new RuntimeException("Effect has not been parsed -> " + source);
    }

    @Override
    public String getRegex() {
        return "\\[([A-Za-z]+) \\{(\\d+)}:\\s(.*)\\s\\{(\\d+)}(?:/.+ \\{\\d+})?]";
    }
}
