package org.bastien.addon.model.entities;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.StringJoiner;

@Getter
@SuperBuilder
public class Mob extends Player {
    protected long idType;

    @Override
    public String toString() {
        return new StringJoiner(", ", Mob.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("idType=" + idType)
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

