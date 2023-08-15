package org.bastien.addon.model.parser.impl;

import org.bastien.addon.model.constant.CombatStyle;
import org.bastien.addon.model.constant.Discipline;
import org.bastien.addon.model.constant.Effect;
import org.bastien.addon.model.entities.EffectBatch;

import java.util.regex.Matcher;

public class DisciplineParser extends RegExpParser<EffectBatch> {

    @Override
    public EffectBatch parse(String source) {
        final Matcher matcher = pattern.matcher(source);
        if (!matcher.matches())
            throw new RuntimeException("Discipline parser could not parse this source -> " + source);
        long effectIdVerify = Long.parseLong(matcher.group(1));
        if (effectIdVerify != Effect.DISCIPLINE_CHANGED.getId())
            throw new RuntimeException("Discipline parser is called on non Discipline changed event.");
        String combatStyleId = matcher.group(2);
        CombatStyle combatStyle = CombatStyle.find(combatStyleId);
        String disciplineId = matcher.group(3);
        Discipline discipline = Discipline.find(disciplineId);
        return new EffectBatch(Effect.DISCIPLINE_CHANGED, combatStyle, discipline);
    }

    @Override
    public String getRegex() {
        return "\\[[A-Za-z]+ \\{(\\d+)}: [^0-9]+ \\{(\\d+)}/.*?\\s\\{(\\d+)}]";
    }
}
