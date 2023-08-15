package org.bastien.addon.parser.impl;

import org.bastien.addon.model.constant.CombatStyle;
import org.bastien.addon.model.constant.Discipline;
import org.bastien.addon.model.constant.Effect;
import org.bastien.addon.model.entities.EffectBatch;
import org.bastien.addon.model.parser.impl.DisciplineParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisciplineParserTest {

    @Test
    public void parsePowertech() {
        DisciplineParser parser = new DisciplineParser();
        EffectBatch actual = parser.parse("[DisciplineChanged {836045448953665}: Powertech {16141007401395916385}/Pyrotech {2031339142381602}]");
        EffectBatch expected = new EffectBatch(Effect.DISCIPLINE_CHANGED, CombatStyle.POWERTECH, Discipline.PYROTECH);
        assertEquals(expected.getEffect(), actual.getEffect());
        assertEquals(expected.getCombatStyle(), actual.getCombatStyle());
        assertEquals(expected.getDiscipline(), actual.getDiscipline());
        assertEquals(expected.getEnergyType().isEmpty(), actual.getEnergyType().isEmpty());
        assertEquals(expected.getEffectType().isEmpty(), actual.getEffectType().isEmpty());
        assertEquals(expected.getAbility().isEmpty(), actual.getAbility().isEmpty());
        assertEquals(expected.getDamage().isEmpty(), actual.getDamage().isEmpty());
    }

    @Test
    public void parseEmptyDiscipline() {
        DisciplineParser parser = new DisciplineParser();
        EffectBatch actual = parser.parse("[DisciplineChanged {836045448953665}: Sage {16140939761890536394}/ {0}]");
        EffectBatch expected = new EffectBatch(Effect.DISCIPLINE_CHANGED, CombatStyle.SAGE, Discipline.UNDEFINED_DISCIPLINE);
        assertEquals(expected.getEffect(), actual.getEffect());
        assertEquals(expected.getCombatStyle(), actual.getCombatStyle());
        assertEquals(expected.getDiscipline(), actual.getDiscipline());
        assertEquals(expected.getEnergyType().isEmpty(), actual.getEnergyType().isEmpty());
        assertEquals(expected.getEffectType().isEmpty(), actual.getEffectType().isEmpty());
        assertEquals(expected.getAbility().isEmpty(), actual.getAbility().isEmpty());
        assertEquals(expected.getDamage().isEmpty(), actual.getDamage().isEmpty());
    }
}
