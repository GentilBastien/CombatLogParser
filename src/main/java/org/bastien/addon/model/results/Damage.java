package org.bastien.addon.model.results;

import org.bastien.addon.model.constant.DamageType;

import java.util.StringJoiner;

public record Damage(long totalDamage, long effectiveDamage, long overDamage, long encochedDamage, boolean isCritical,
                     boolean isShielded, DamageType damageType, DamageType causeDamage, long absorbedDamage,
                     long reflectedDamage) {

    @Override
    public String toString() {
        return new StringJoiner(", ", Damage.class.getSimpleName() + "[", "]")
                .add("totalDamage=" + totalDamage)
                .add("effectiveDamage=" + effectiveDamage)
                .add("overDamage=" + overDamage)
                .add("encochedDamage=" + encochedDamage)
                .add("isCritical=" + isCritical)
                .add("isShielded=" + isShielded)
                .add("damageType=" + damageType)
                .add("causeDamage=" + causeDamage)
                .add("absorbedDamage=" + absorbedDamage)
                .add("reflectedDamage=" + reflectedDamage)
                .toString();
    }
}
