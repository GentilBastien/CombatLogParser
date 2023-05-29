package org.bastien.addon.model.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.StringJoiner;
@Getter
@SuperBuilder
public class Pet extends Mob {
    @NonNull
    protected String nameOwner;
    protected long idOwner;

    @Override
    public String toString() {
        return new StringJoiner(", ", Pet.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("idType=" + idType)
                .add("idOwner=" + idOwner)
                .add("nameOwner=" + nameOwner)
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
