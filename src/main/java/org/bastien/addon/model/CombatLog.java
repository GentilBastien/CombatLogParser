package org.bastien.addon.model;

import lombok.NonNull;
import org.bastien.addon.model.constant.Event;
import org.bastien.addon.model.entities.Ability;
import org.bastien.addon.model.entities.EffectBatch;
import org.bastien.addon.model.entities.Player;

import java.time.LocalTime;

public record CombatLog(
        boolean isPvp,
        @NonNull LocalTime timestamp,
        Player source,
        Player target,
        Ability ability,
        Event event,
        EffectBatch effect,
        Object value,
        @NonNull String rawLog
) {
}