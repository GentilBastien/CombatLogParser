package org.bastien.addon.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Location {
    NOVARE_COAST(137438992654L),
    QUESHBALL(137438953518L),
    VOIDSTAR(137438989517L),
    CIVIL_WAR(137438956902L),
    HYPERGATE(137438993104L),
    NO_PvP_LOCATION(-1L);

    private final long id;

    public static Location find(long id) {
        return Arrays.stream(Location.values())
                .filter(event -> event.id == id)
                .findFirst()
                .orElse(Location.NO_PvP_LOCATION);
    }
}
