package org.bastien.addon.parser.impl;

import org.bastien.addon.model.constant.DamageType;
import org.bastien.addon.model.results.Damage;
import org.bastien.addon.parser.impl.DamageParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DamageParserTest {

    @Test
    public void parseCriticalDamage() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(5485* internal {836045448940876}) <5485>");
        Damage expected = new Damage(5485, 5485, 0, 5485, true, false, DamageType.INTERNAL, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEquals(expected.totalDamage(), actual.totalDamage());
        assertEquals(expected.effectiveDamage(), actual.effectiveDamage());
        assertEquals(expected.overDamage(), actual.overDamage());
        assertEquals(expected.encochedDamage(), actual.encochedDamage());
        assertEquals(expected.isCritical(), actual.isCritical());
        assertEquals(expected.isShielded(), actual.isShielded());
        assertEquals(expected.damageType(), actual.damageType());
        assertEquals(expected.causeDamage(), actual.causeDamage());
        assertEquals(expected.absorbedDamage(), actual.absorbedDamage());
        assertEquals(expected.reflectedDamage(), actual.reflectedDamage());
    }

    @Test
    public void parseImmuneDamageWithEnergyType() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(23022 ~0 energy {836045448940874} -immune {836045448945506}) <23022>");
        Damage expected = new Damage(23022, 0, 23022, 23022, false, false, DamageType.ENERGY, DamageType.IMMUNE, 0, 0);
        assertEquals(expected.totalDamage(), actual.totalDamage());
        assertEquals(expected.effectiveDamage(), actual.effectiveDamage());
        assertEquals(expected.overDamage(), actual.overDamage());
        assertEquals(expected.encochedDamage(), actual.encochedDamage());
        assertEquals(expected.isCritical(), actual.isCritical());
        assertEquals(expected.isShielded(), actual.isShielded());
        assertEquals(expected.damageType(), actual.damageType());
        assertEquals(expected.causeDamage(), actual.causeDamage());
        assertEquals(expected.absorbedDamage(), actual.absorbedDamage());
        assertEquals(expected.reflectedDamage(), actual.reflectedDamage());
    }

    @Test
    public void parseCriticalShieldedDamage() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(15470* ~10513 energy {836045448940874} -shield {836045448945509} (15857 absorbed {836045448945511}))");
        Damage expected = new Damage(15470, 10513, 4957, 0, true, true, DamageType.ENERGY, DamageType.SHIELD, 15857, 0);
        assertEquals(expected.totalDamage(), actual.totalDamage());
        assertEquals(expected.effectiveDamage(), actual.effectiveDamage());
        assertEquals(expected.overDamage(), actual.overDamage());
        assertEquals(expected.encochedDamage(), actual.encochedDamage());
        assertEquals(expected.isCritical(), actual.isCritical());
        assertEquals(expected.isShielded(), actual.isShielded());
        assertEquals(expected.damageType(), actual.damageType());
        assertEquals(expected.causeDamage(), actual.causeDamage());
        assertEquals(expected.absorbedDamage(), actual.absorbedDamage());
        assertEquals(expected.reflectedDamage(), actual.reflectedDamage());
    }

    @Test
    public void parseReflectedDamage() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(8439 kinetic {836045448940873}(reflected {836045448953649}))");
        Damage expected = new Damage(8439, 8439, 0, 0, false, false, DamageType.KINETIC, DamageType.REFLECTED, 0, 8439);
        assertEquals(expected.totalDamage(), actual.totalDamage());
        assertEquals(expected.effectiveDamage(), actual.effectiveDamage());
        assertEquals(expected.overDamage(), actual.overDamage());
        assertEquals(expected.encochedDamage(), actual.encochedDamage());
        assertEquals(expected.isCritical(), actual.isCritical());
        assertEquals(expected.isShielded(), actual.isShielded());
        assertEquals(expected.damageType(), actual.damageType());
        assertEquals(expected.causeDamage(), actual.causeDamage());
        assertEquals(expected.absorbedDamage(), actual.absorbedDamage());
        assertEquals(expected.reflectedDamage(), actual.reflectedDamage());
    }

    @Test
    public void parseImmuneDamageDealingZero() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(0 -immune {836045448945506}) <2404>");
        Damage expected = new Damage(0, 0, 0, 2404, false, false, DamageType.IMMUNE, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEquals(expected.totalDamage(), actual.totalDamage());
        assertEquals(expected.effectiveDamage(), actual.effectiveDamage());
        assertEquals(expected.overDamage(), actual.overDamage());
        assertEquals(expected.encochedDamage(), actual.encochedDamage());
        assertEquals(expected.isCritical(), actual.isCritical());
        assertEquals(expected.isShielded(), actual.isShielded());
        assertEquals(expected.damageType(), actual.damageType());
        assertEquals(expected.causeDamage(), actual.causeDamage());
        assertEquals(expected.absorbedDamage(), actual.absorbedDamage());
        assertEquals(expected.reflectedDamage(), actual.reflectedDamage());
    }

    @Test
    public void parseCoverDamageDealingOne() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(0 -cover {836045448945510}) <1>");
        Damage expected = new Damage(0, 0, 0, 1, false, false, DamageType.COVER, DamageType.NO_DAMAGE_TYPE, 0, 0);
        assertEquals(expected.totalDamage(), actual.totalDamage());
        assertEquals(expected.effectiveDamage(), actual.effectiveDamage());
        assertEquals(expected.overDamage(), actual.overDamage());
        assertEquals(expected.encochedDamage(), actual.encochedDamage());
        assertEquals(expected.isCritical(), actual.isCritical());
        assertEquals(expected.isShielded(), actual.isShielded());
        assertEquals(expected.damageType(), actual.damageType());
        assertEquals(expected.causeDamage(), actual.causeDamage());
        assertEquals(expected.absorbedDamage(), actual.absorbedDamage());
        assertEquals(expected.reflectedDamage(), actual.reflectedDamage());
    }

    @Test
    public void parseShieldedDamage() {
        DamageParser parser = new DamageParser();
        Damage actual = parser.parse("(3800 kinetic {836045448940873} -shield {836045448945509}) <9499>");
        Damage expected = new Damage(3800, 3800, 0, 9499, false, true, DamageType.KINETIC, DamageType.SHIELD, 0, 0);
        assertEquals(expected.totalDamage(), actual.totalDamage());
        assertEquals(expected.effectiveDamage(), actual.effectiveDamage());
        assertEquals(expected.overDamage(), actual.overDamage());
        assertEquals(expected.encochedDamage(), actual.encochedDamage());
        assertEquals(expected.isCritical(), actual.isCritical());
        assertEquals(expected.isShielded(), actual.isShielded());
        assertEquals(expected.damageType(), actual.damageType());
        assertEquals(expected.causeDamage(), actual.causeDamage());
        assertEquals(expected.absorbedDamage(), actual.absorbedDamage());
        assertEquals(expected.reflectedDamage(), actual.reflectedDamage());
    }
}
