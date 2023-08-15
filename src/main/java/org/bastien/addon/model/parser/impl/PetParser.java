package org.bastien.addon.model.parser.impl;

import org.bastien.addon.model.entities.Pet;

import java.util.regex.Matcher;

public class PetParser extends RegExpParser<Pet> {

    @Override
    public Pet parse(String source) {
        if (source.equals("[]"))
            return null;
        final Matcher matcher = pattern.matcher(source);
        if (!matcher.matches())
            throw new RuntimeException("The Pet parser did not match this source -> " + source);

        String owner = matcher.group(1);
        String[] ownerInfos = owner.split("#");
        String nameOwner = ownerInfos[0];
        long idOwner = ownerInfos.length > 1 ? Long.parseLong(ownerInfos[1]) : -1;

        String name = matcher.group(2);
        long id = Long.parseLong(matcher.group(3));
        long idType = Long.parseLong(matcher.group(4));
        double x = Double.parseDouble(matcher.group(5));
        double y = Double.parseDouble(matcher.group(6));
        double z = Double.parseDouble(matcher.group(7));
        double angle = Double.parseDouble(matcher.group(8));
        long health = Long.parseLong(matcher.group(9));
        long maxHealth = Long.parseLong(matcher.group(10));

        return Pet.builder().id(id).idType(idType).name(name).idOwner(idOwner).nameOwner(nameOwner).positionX(x)
                .positionY(y).positionZ(z).angle(angle).health(health).maxHealth(maxHealth).build();
    }

    @Override
    public String getRegex() {
        return "\\[@([^0-9]+(?:#\\d+)?)/(.*) \\{(\\d+)}:(\\d+)\\|\\((-?\\d+\\.\\d+),(-?\\d+\\.\\d+),(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)\\)\\|\\((\\d+)/(\\d+)\\)]";
    }
}
