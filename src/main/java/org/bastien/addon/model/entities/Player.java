package org.bastien.addon.model.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.StringJoiner;

@Getter
@SuperBuilder
public class Player {
    protected long id;
    @NonNull
    protected String name;
    protected double positionX;
    protected double positionY;
    protected double positionZ;
    protected double angle;
    protected long health;
    protected long maxHealth;

    @Override
    public String toString() {
        return new StringJoiner(", ", Player.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("positionX=" + positionX)
                .add("positionY=" + positionY)
                .add("positionZ=" + positionZ)
                .add("angle=" + angle)
                .add("health=" + health)
                .add("maxHealth=" + maxHealth)
                .toString();
    }
}
