package org.bastien.addon.model.parser.impl;

import org.bastien.addon.model.constant.DamageType;
import org.bastien.addon.model.parser.ParserFactory;
import org.bastien.addon.model.results.Damage;

import java.util.regex.Matcher;

public class DamageParser extends RegExpParser<Damage> {
    private final EncochedNumberParser encochedNumberParser;
    private final AbsorbedParser absorbedParser;

    public DamageParser() {
        this.encochedNumberParser = ParserFactory.getInstance(EncochedNumberParser.class);
        this.absorbedParser = ParserFactory.getInstance(AbsorbedParser.class);
    }

    @Override
    public Damage parse(String source) {
        final Matcher matcher = pattern.matcher(source);
        if (!matcher.find())
            throw new RuntimeException("The damage parser could not parse this source -> " + source);
        long totalDamage = extractTotalDamage(source);
        long effectiveDamage = extractEffectiveDamage(source, totalDamage);
        long overDamage = totalDamage - effectiveDamage;
        long damageTypeId = Long.parseLong(matcher.group(1));
        DamageType damageType = DamageType.from(damageTypeId);
        long absorbed = 0, reflected = 0;
        DamageType causeDamageType = DamageType.NO_DAMAGE_TYPE;
        if (matcher.find()) {
            long causeDamageId = Long.parseLong(matcher.group(1));
            causeDamageType = DamageType.from(causeDamageId);
            switch (causeDamageType) {
                case ABSORBED, SHIELD -> {
                    try {
                        absorbed = absorbedParser.parse(source);
                    } catch (RuntimeException e) {
                        System.err.println("Warning : Absorbed parser did not found the absorbed damage amount in this source -> " + source);
                    }
                }
                case REFLECTED -> reflected = totalDamage;
                case IMMUNE -> {}
                default -> throw new RuntimeException("Invalid cause damage -> " + causeDamageId);
            }
        }
        long encochedDamage = 0;
        try {
            encochedDamage = encochedNumberParser.parse(source);
        } catch (RuntimeException ignored) {
        }
        return new Damage(
                totalDamage, effectiveDamage, overDamage, encochedDamage, source.contains("*"),
                causeDamageType.equals(DamageType.SHIELD), damageType, causeDamageType, absorbed, reflected);
    }

    @Override
    public String getRegex() {
        return "\\{(\\d+)}";
    }

    private long extractTotalDamage(String source) {
        int parenthesisIndex = source.indexOf('(');
        int spaceIndex = source.indexOf(' ', parenthesisIndex);
        return Long.parseLong(source.substring(parenthesisIndex + 1, spaceIndex).replace("*", ""));
    }

    private long extractEffectiveDamage(String source, long totalDamage) {
        int tildeIndex = source.indexOf('~');
        if (tildeIndex == -1)
            return totalDamage;
        int spaceIndex = source.indexOf(' ', tildeIndex);
        return Long.parseLong(source.substring(tildeIndex + 1, spaceIndex));
    }
}
