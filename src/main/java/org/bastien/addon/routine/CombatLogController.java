package org.bastien.addon.routine;

import lombok.NonNull;
import org.bastien.addon.model.CombatLog;
import org.bastien.addon.model.parser.CombatLogParser;
import org.bastien.addon.service.CombatLogService;
import org.jetbrains.annotations.NotNull;

public class CombatLogController {
    private final CombatLogParser combatLogParser;
    private final CombatLogService combatLogService;
    private long linesParsed = 0;

    public CombatLogController() {
        this.combatLogParser = CombatLogParser.getInstance();
        this.combatLogService = CombatLogService.getInstance();
    }

    public void onNext(@NonNull String s) {
        try {
            final CombatLog combatLog = combatLogParser.parse(s);
            this.linesParsed++;
            System.out.println("line " + linesParsed + " next -> " + combatLog);
            if (combatLog.isPvp()) {
                combatLogService.addToBuffer(combatLog);
            }
        } catch (RuntimeException e) {
            System.err.println("Error parsing line -> " + linesParsed + " with error: " + e.getMessage());
            onException(e);
        }
    }

    public void onError(@NotNull Throwable e) {
        this.linesParsed = 0;
        onException(e);
    }

    public void onComplete() {
        this.linesParsed = 0;
    }

    private void onException(Throwable e) {
        e.printStackTrace(System.err);
        System.exit(0);
    }
}
