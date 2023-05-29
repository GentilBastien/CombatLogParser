package org.bastien.addon.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Event {
    FAILED_EFFECT(836045448945499L),
    SET_LEVEL(836045448945492L),
    ABILITY_DEACTIVATE(836045448945480L),
    ABILITY_ACTIVATE(836045448945479L),
    ABILITY_CANCEL(836045448945481L),
    ABILITY_INTERRUPT(836045448945482L),
    TARGET_SET(836045448953668L),
    TARGET_CLEARED(836045448953669L),
    MODIFY_THREAT(836045448945483L),
    ENTER_COVER(836045448945485L),
    LEAVE_COVER(836045448945486L),
    CROUCH(836045448945487L),
    TAUNT(836045448945488L),
    FALLING_DAMAGE(836045448945484L),
    ENTER_COMBAT(836045448945489L),
    EXIT_COMBAT(836045448945490L),
    DEATH(836045448945493L),
    REVIVED(836045448945494L),
    NO_LONGER_SUSPICIOUS(836045448945498L);

    private final long id;

    public static Event from(long id) {
        return Arrays.stream(Event.values())
                .filter(event -> event.id == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No event matching id " + id));
    }
}
