package org.bastien.addon.parser.impl;

import org.bastien.addon.model.constant.DamageType;
import org.bastien.addon.model.parser.impl.DamageParser;
import org.bastien.addon.model.results.Damage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DamageParserTest {

    private void assertEqualsDamages(Damage expected, Damage actual) {
        assertEquals(expected.totalDamage(), actual.totalDamage());
        assertEquals(expected.effectiveDamage(), actual.effectiveDamage());
        assertEquals(expected.overDamage(), actual.overDamage());
        assertEquals(expected.threatDamage(), actual.threatDamage());
        assertEquals(expected.isCritical(), actual.isCritical());
        assertEquals(expected.isShielded(), actual.isShielded());
        assertEquals(expected.damageType(), actual.damageType());
        assertEquals(expected.causeDamage(), actual.causeDamage());
        assertEquals(expected.absorbedDamage(), actual.absorbedDamage());
        assertEquals(expected.reflectedDamage(), actual.reflectedDamage());
    }

    @Test
    public void parseDamage1() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(5485* internal {836045448940876}) <5485>");
        Damage expected = new Damage(5485, 5485, 0, 5485, true, false, DamageType.INTERNAL, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage2() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(23022 ~0 energy {836045448940874} -immune {836045448945506}) <23022>");
        Damage expected = new Damage(23022, 0, 23022, 23022, false, false, DamageType.ENERGY, DamageType.IMMUNE, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage3() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(15470* ~10513 energy {836045448940874} -shield {836045448945509} (15857 absorbed {836045448945511}))");
        Damage expected = new Damage(15470, 10513, 4957, 0, true, true, DamageType.ENERGY, DamageType.SHIELD, 15857, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage4() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(8439 kinetic {836045448940873}(reflected {836045448953649}))");
        Damage expected = new Damage(8439, 8439, 0, 0, false, false, DamageType.KINETIC, DamageType.REFLECTED, 0, 8439);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage5() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(0 -immune {836045448945506}) <2404>");
        Damage expected = new Damage(0, 0, 0, 2404, false, false, DamageType.IMMUNE, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage6() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(0 -cover {836045448945510}) <1>");
        Damage expected = new Damage(0, 0, 0, 1, false, false, DamageType.COVER, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage7() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(0 -)");
        Damage expected = new Damage(0, 0, 0, 0, false, false, DamageType.NO_DAMAGE_TYPE, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage8() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(0 -) <2134>");
        Damage expected = new Damage(0, 0, 0, 2134, false, false, DamageType.NO_DAMAGE_TYPE, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage9() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(48950 )");
        Damage expected = new Damage(48950, 0, 0, 0, false, false, DamageType.NO_DAMAGE_TYPE, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage10() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(3800 kinetic {836045448940873} -shield {836045448945509}) <9499>");
        Damage expected = new Damage(3800, 3800, 0, 9499, false, true, DamageType.KINETIC, DamageType.SHIELD, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage11() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(6140 ~3807 kinetic {836045448940873} (2332 absorbed {836045448945511})) <6140>");
        Damage expected = new Damage(6140, 3807, 2333, 6140, false, false, DamageType.KINETIC, DamageType.ABSORBED, 2332, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage12() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(0 -shield {836045448945509}) <295>");
        Damage expected = new Damage(0, 0, 0, 295, false, false, DamageType.SHIELD, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage13() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(0 -deflect {836045448945508}) <1>");
        Damage expected = new Damage(0, 0, 0, 1, false, false, DamageType.DEFLECT, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEqualsDamages(expected, actual);
    }

    @Test
    public void parseDamage14() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(68716516 -)");
        Damage expected = new Damage(68716516, 0, 0, 0, false, false, DamageType.NO_DAMAGE_TYPE, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEqualsDamages(expected, actual);
    }
}