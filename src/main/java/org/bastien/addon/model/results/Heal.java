package org.bastien.addon.model.results;

import java.util.StringJoiner;

public record Heal(long totalHeal, long effectiveHeal, long overHeal, boolean isCritical) {

    @Override
    public String toString() {
        return new StringJoiner(", ", Heal.class.getSimpleName() + "[", "]")
                .add("totalHeal=" + totalHeal)
                .add("effectiveHeal=" + effectiveHeal)
                .add("overHeal=" + overHeal)
                .add("isCritical=" + isCritical)
                .toString();
    }
}
