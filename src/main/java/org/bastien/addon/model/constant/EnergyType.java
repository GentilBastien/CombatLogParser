package org.bastien.addon.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EnergyType {
    FORCE(836045448938502L),
    ENERGY(836045448938503L),
    FOCUS_POINT(836045448938496L),
    RAGE_POINT(836045448938497L),
    HEALTH_POINT(836045448938504L);

    private final long id;

    public static EnergyType find(long id) {
        return Arrays.stream(EnergyType.values())
                .filter(event -> event.id == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No energy type matching id " + id));
    }
}
