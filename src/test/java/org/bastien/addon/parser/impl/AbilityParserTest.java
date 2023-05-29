package org.bastien.addon.parser.impl;

import org.bastien.addon.model.entities.Ability;
import org.bastien.addon.parser.impl.AbilityParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbilityParserTest {

    @Test
    public void parseEmptyAbility() {
        AbilityParser parser = new AbilityParser();
        Ability actual = parser.parse("[ {2850501074878464}]");
        Ability expected = new Ability(2850501074878464L, "", false);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.isBuff(), actual.isBuff());
    }

    @Test
    public void parseNormalAbility() {
        AbilityParser parser = new AbilityParser();
        Ability actual = parser.parse("[Rage Pounce {3380019002867712}]");
        Ability expected = new Ability(3380019002867712L, "Rage Pounce", false);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.isBuff(), actual.isBuff());
    }

    @Test
    public void parseWeirdAbility() {
        AbilityParser parser = new AbilityParser();
        Ability actual = parser.parse("[Aze' frds _ dt {0}]");
        Ability expected = new Ability(0L, "Aze' frds _ dt", false);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.isBuff(), actual.isBuff());
    }
}
