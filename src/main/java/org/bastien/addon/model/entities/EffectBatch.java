package org.bastien.addon.model.entities;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.bastien.addon.model.constant.*;
import org.bastien.addon.model.results.Damage;

import java.util.Optional;
import java.util.StringJoiner;

@SuperBuilder
public class EffectBatch {
    @NonNull
    private Effect effect;
    private EffectType effectType;
    private EnergyType energyType;
    private Ability ability;
    private Damage damage;
    private CombatStyle combatStyle;
    private Discipline discipline;

    public EffectBatch(@NonNull Effect effect, EffectType effectType) {
        this.effect = effect;
        this.effectType = effectType;
    }

    public EffectBatch(@NonNull Effect effect, EffectType effectType, Ability ability) {
        this.effect = effect;
        this.effectType = effectType;
        this.ability = ability;
    }

    public EffectBatch(@NonNull Effect effect, EnergyType energyType) {
        this.effect = effect;
        this.energyType = energyType;
    }

    public EffectBatch(@NonNull Effect effect, CombatStyle combatStyle, Discipline discipline) {
        this.effect = effect;
        this.combatStyle = combatStyle;
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", EffectBatch.class.getSimpleName() + "[", "]");
        sj.add("effect=" + effect);
        if (effectType != null) sj.add("effectType=" + effectType);
        if (energyType != null) sj.add("energyType=" + energyType);
        if (ability != null) sj.add("ability=" + ability);
        if (damage != null) sj.add("damage=" + damage);
        if (combatStyle != null) sj.add("combatStyle=" + combatStyle);
        if (discipline != null) sj.add("discipline=" + discipline);
        return sj.toString();
    }

    public @NonNull Effect getEffect() {
        return effect;
    }

    public Optional<EffectType> getEffectType() {
        return Optional.ofNullable(effectType);
    }

    public Optional<EnergyType> getEnergyType() {
        return Optional.ofNullable(energyType);
    }

    public Optional<Ability> getAbility() {
        return Optional.ofNullable(ability);
    }

    public Optional<Damage> getDamage() {
        return Optional.ofNullable(damage);
    }

    public Optional<CombatStyle> getCombatStyle() {
        return Optional.ofNullable(combatStyle);
    }

    public Optional<Discipline> getDiscipline() {
        return Optional.ofNullable(discipline);
    }
}
