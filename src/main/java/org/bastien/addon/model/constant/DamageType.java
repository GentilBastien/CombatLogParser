package org.bastien.addon.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum DamageType {
    ENERGY(836045448940874L),
    KINETIC(836045448940873L),
    INTERNAL(836045448940876L),
    ELEMENTAL(836045448940875L),
    ABSORBED(836045448945511L),
    REFLECTED(836045448953649L),
    MISS(836045448945502L),
    PARRY(836045448945503L),
    DODGE(836045448945505L),
    RESIST(836045448945507L),
    DEFLECT(836045448945508L),
    SHIELD(836045448945509L),
    COVER(836045448945510L),
    IMMUNE(836045448945506L),
    NO_DAMAGE_TYPE(-1L);

    private final long id;

    public static DamageType from(long id) {
        return Arrays.stream(DamageType.values())
                .filter(event -> event.id == id)
                .findFirst()
                .orElse(DamageType.NO_DAMAGE_TYPE);
    }
}
