package org.bastien.addon.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CombatStyle {
    POWERTECH("16141007401395916385"), VANGUARD("16141087184558207941"),
    MERCENARY("16141111589108060476"), COMMANDO("16141067504602942620"),
    SNIPER("16141046347418927959"), GUNSLINGER("16141041084185282043"),
    OPERATIVE("16140905232405801950"), SCOUNDREL("16141067128654459200"),
    SORCERER("16141067119934185414"), SAGE("16140939761890536394"),
    JUGGERNAUT("16141180228828243745"), GUARDIAN("16140975849784542883"),
    MARAUDER("16141024490216983174"), SENTINEL("16141154905109553504"),
    ASSASSIN("16141163438392504574"), SHADOW("16141082698337403481"),
    UNDEFINED_COMBAT_STYLE("0");

    private final String id;

    public static CombatStyle find(String id) {
        return Arrays.stream(CombatStyle.values())
                .filter(item -> item.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No combat style found with id " + id));
    }
}
