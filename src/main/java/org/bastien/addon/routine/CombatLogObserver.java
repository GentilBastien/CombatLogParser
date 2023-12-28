package org.bastien.addon.routine;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public class CombatLogObserver implements Observer<String> {
    private final CombatLogController combatLogController;
    private Disposable disposable;

    public CombatLogObserver() {
        this.combatLogController = new CombatLogController();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(@NonNull String s) {
        combatLogController.onNext(s);
    }

    @Override
    public void onError(@NotNull Throwable e) {
        disposable.dispose();
        combatLogController.onError(e);
    }

    @Override
    public void onComplete() {
        disposable.dispose();
        combatLogController.onComplete();
    }
}
