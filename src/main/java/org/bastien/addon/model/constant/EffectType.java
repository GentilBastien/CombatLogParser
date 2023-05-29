package org.bastien.addon.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EffectType {
    HEAL(836045448945500L),
    DAMAGE(836045448945501L),
    TAUNT(1971557492588811L),
    STUNNED(2027503736586501L),
    STUNNED_PHYSICAL(807178973741312L),
    STUNNED_FORCE(807754499359098L),
    STUNNED_TECH(4325856700793092L),
    INCAPACITATED_MENTAL(3828636926869788L),
    INCAPACITATED_PHYSICAL(1962284658196739L),
    INCAPACITATED_PHYSICAL_2(1962349082706176L),
    IMMOBILIZED_PHYSICAL(807750204391700L),
    IMMOBILIZED_FORCE(808282780336925L),
    IMMOBILIZED_TECH(3384704812187915L),
    SLOWED_PHYSICAL(3066602354376964L),
    SLOWED_FORCE(1148263801553204L),
    SLOWED_TECH(3385271747871005L),
    BUFF(-1L);

    private final long id;

    public static EffectType find(long id) {
        return Arrays.stream(EffectType.values())
                .filter(event -> event.id == id)
                .findFirst()
                .orElse(EffectType.BUFF);
    }
}
