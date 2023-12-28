package org.bastien.addon.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EffectAction {
    APPLY_EFFECT(836045448945477L),
    REMOVE_EFFECT(836045448945478L),
    MODIFY_CHARGES(836045448953666L),
    SPEND(836045448945473L),
    RESTORE(836045448945476L),
    AREA_ENTERED(836045448953664L),
    DISCIPLINE_CHANGED(836045448953665L);

    private final long id;

    public static EffectAction find(long id) {
        return Arrays.stream(EffectAction.values())
                .filter(effect -> effect.id == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No effectAction matching id " + id));
    }
}
