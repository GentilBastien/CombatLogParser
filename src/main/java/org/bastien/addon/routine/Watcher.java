package org.bastien.addon.routine;

import java.nio.file.Path;

public final class Watcher {
    private static final Watcher instance = new Watcher();
    private final LogObserver observer;
    private FolderWatcherTask task;
    private Path folderPath;
    private Thread threadWatcher;

    private Watcher() {
        this.observer = new LogObserver();
        this.folderPath = null;
        this.task = null;
        this.threadWatcher = null;
    }

    public static Watcher getInstance() {
        return instance;
    }

    public void setCombatLogDirectory(final Path newCombatLogDirectoryPath) {
        assert newCombatLogDirectoryPath != null;
        if (newCombatLogDirectoryPath.equals(folderPath)) {
            System.out.println("The same folder has been set.");
            return;
        }
        System.out.println("FOLDER HAS BEEN CHANGED -> " + newCombatLogDirectoryPath);
        folderPath = newCombatLogDirectoryPath;
        closeStream();
        reOpenStream();
    }

    private void closeStream() {
        if (task != null)
            task.doOnClose();
        if (threadWatcher != null)
            threadWatcher.interrupt();
        task = null;
    }

    private void reOpenStream() {
        task = new FolderWatcherTask(folderPath, observer);
        threadWatcher = new Thread(task);
        threadWatcher.start();
    }
}
