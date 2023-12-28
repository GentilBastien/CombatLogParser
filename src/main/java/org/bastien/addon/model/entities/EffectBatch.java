package org.bastien.addon.model.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.bastien.addon.model.constant.*;

import java.util.StringJoiner;

@Getter
@SuperBuilder
public class EffectBatch {
    @NonNull
    private EffectAction action;
    private EffectType type;
    private EnergyType energyType;
    private Ability ability;
    private CombatStyle combatStyle;
    private Discipline discipline;
    private Location location;

    public EffectBatch(@NonNull EffectAction action, EffectType type) {
        this.action = action;
        this.type = type;
    }

    public EffectBatch(@NonNull EffectAction action, EffectType type, Ability ability) {
        this.action = action;
        this.type = type;
        this.ability = ability;
    }

    public EffectBatch(@NonNull EffectAction action, EnergyType energyType) {
        this.action = action;
        this.energyType = energyType;
    }

    public EffectBatch(@NonNull EffectAction action, CombatStyle combatStyle, Discipline discipline) {
        this.action = action;
        this.combatStyle = combatStyle;
        this.discipline = discipline;
    }

    public EffectBatch(@NonNull EffectAction action, Location location) {
        this.action = action;
        this.location = location;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", EffectBatch.class.getSimpleName() + "[", "]");
        sj.add("effectAction=" + action);
        if (type != null) sj.add("effectType=" + type);
        if (energyType != null) sj.add("energyType=" + energyType);
        if (ability != null) sj.add("ability=" + ability);
        if (combatStyle != null) sj.add("combatStyle=" + combatStyle);
        if (discipline != null) sj.add("discipline=" + discipline);
        if (location != null) sj.add("location=" + location);
        return sj.toString();
    }
}
