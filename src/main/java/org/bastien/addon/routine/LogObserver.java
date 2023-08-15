package org.bastien.addon.routine;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.NonNull;
import org.bastien.addon.model.CombatLog;
import org.bastien.addon.model.parser.CombatLogParser;

public class LogObserver implements Observer<String> {
    private final CombatLogParser combatLogParser = CombatLogParser.getInstance();
    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(@NonNull String s) {
        CombatLog combatLog = combatLogParser.parse(s);
        System.out.println("next -> " + combatLog);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        disposable.dispose();
        System.exit(0);
    }

    @Override
    public void onComplete() {
        disposable.dispose();
    }
}
