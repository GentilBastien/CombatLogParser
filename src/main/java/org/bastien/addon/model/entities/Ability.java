package org.bastien.addon.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ability {
    private long id;
    private String name;
    private boolean isBuff;
}
