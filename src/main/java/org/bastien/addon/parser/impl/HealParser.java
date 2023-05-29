package org.bastien.addon.parser.impl;

import org.bastien.addon.model.results.Heal;

import java.util.regex.Matcher;

public class HealParser extends RegExpParser<Heal> {

    public static void main(String[] args) {
        HealParser parser = new HealParser();
        System.out.println(parser.parse("(925* ~599) <299>"));
        System.out.println(parser.parse("(69 ~0)"));
        System.out.println(parser.parse("(2324*)"));
        System.out.println(parser.parse("(545* ~150)"));
    }

    @Override
    public Heal parse(String source) {
        final Matcher matcher = pattern.matcher(source);
        if (!matcher.find())
            return null;
        long totalHeal = Long.parseLong(matcher.group(1));
        long effectiveHeal = extractEffectiveHeal(source, totalHeal);
        long overHeal = totalHeal - effectiveHeal;
        return new Heal(totalHeal, effectiveHeal, overHeal, source.contains("*"));
    }

    @Override
    public String getRegex() {
        return "\\((\\d+)";
    }

    private long extractEffectiveHeal(String source, long totalHeal) {
        int tildeIndex = source.indexOf('~');
        if (tildeIndex == -1)
            return totalHeal;
        int spaceIndex = source.indexOf(')', tildeIndex);
        return Long.parseLong(source.substring(tildeIndex + 1, spaceIndex));
    }
}
