package org.bastien.addon.parser.impl;

import org.bastien.addon.model.entities.Mob;

import java.util.regex.Matcher;

public class MobParser extends RegExpParser<Mob> {

    @Override
    public Mob parse(String source) {
        if (source.equals("[]"))
            return null;
        final Matcher matcher = pattern.matcher(source);
        if (!matcher.matches()) {
            System.err.println("Warning : Mob parser did not match this source -> " + source);
            return null;
        }

        String name = matcher.group(1);
        long id = Long.parseLong(matcher.group(2));
        long idType = Long.parseLong(matcher.group(3));
        double x = Double.parseDouble(matcher.group(4));
        double y = Double.parseDouble(matcher.group(5));
        double z = Double.parseDouble(matcher.group(6));
        double angle = Double.parseDouble(matcher.group(7));
        long health = Long.parseLong(matcher.group(8));
        long maxHealth = Long.parseLong(matcher.group(9));
        return Mob.builder().id(id).idType(idType).name(name).positionX(x).positionY(y).positionZ(z).angle(angle)
                .health(health).maxHealth(maxHealth).build();
    }

    @Override
    public String getRegex() {
        return "\\[(.*) \\{(\\d+)}:(\\d+)\\|\\((-?\\d+\\.\\d+),(-?\\d+\\.\\d+),(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)\\)\\|\\((\\d+)/(\\d+)\\)]";
    }
}
