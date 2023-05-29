package org.bastien.addon.model;

import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.bastien.addon.model.constant.Event;
import org.bastien.addon.model.entities.Ability;
import org.bastien.addon.model.entities.EffectBatch;
import org.bastien.addon.model.entities.Player;

import java.time.LocalTime;

@ToString
@SuperBuilder
public class CombatLog {
    @NonNull
    private LocalTime timestamp;
    private Player source;
    private Player target;
    private Ability ability;
    private Event event;
    private EffectBatch effect;
    private Object value;
    @NonNull
    private String rawLog;
}
