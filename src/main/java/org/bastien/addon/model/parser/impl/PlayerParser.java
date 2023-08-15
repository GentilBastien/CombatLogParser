package org.bastien.addon.model.parser.impl;

import org.bastien.addon.model.entities.Player;

import java.util.regex.Matcher;

public class PlayerParser extends RegExpParser<Player> {

    public static void main(String[] args) {
        PlayerParser eventParser = new PlayerParser();
        System.out.println(eventParser.parse("[@Candrticher#689860312784490|(40.61,51.84,-15.77,-126.60)|(702/702)]"));
    }

    @Override
    public Player parse(String source) {
        if (source.equals("[]"))
            return null;
        final Matcher matcher = pattern.matcher(source);
        if (!matcher.matches())
            throw new RuntimeException("The Player parser did not match this source -> " + source);
        String name = matcher.group(1);
        long id = Long.parseLong(matcher.group(2));
        double x = Double.parseDouble(matcher.group(3));
        double y = Double.parseDouble(matcher.group(4));
        double z = Double.parseDouble(matcher.group(5));
        double angle = Double.parseDouble(matcher.group(6));
        long health = Long.parseLong(matcher.group(7));
        long maxHealth = Long.parseLong(matcher.group(8));
        return Player.builder().id(id).name(name).positionX(x).positionY(y).positionZ(z).angle(angle).health(health)
                .maxHealth(maxHealth).build();
    }

    @Override
    public String getRegex() {
        return "\\[@([^0-9]+)#(\\d+)\\|\\((-?\\d+\\.\\d+),(-?\\d+\\.\\d+),(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)\\)\\|\\((\\d+)/(\\d+)\\)]";
    }
}
