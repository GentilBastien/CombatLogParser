package org.bastien.addon.routine;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

public final class FolderWatcherTask implements Runnable {
    private static final Pattern COMBAT_LOG_NAME_PATTERN = Pattern.compile("combat_+\\d{4}-\\d{2}-\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{6}.txt");
    private final Path folderPath;
    private final LogObserver observer;
    private WatchService watchService;
    private Path filePath;
    private FileWatcherTask task;
    private Thread threadWatcher;
    private boolean run;

    FolderWatcherTask(final Path folderPath, LogObserver observer) {
        this.folderPath = folderPath;
        this.observer = observer;
        this.filePath = null;
        this.task = null;
        this.threadWatcher = null;
        this.run = true;
        try {
            this.watchService = FileSystems.getDefault().newWatchService();
            this.folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            doOnClose();
        }
    }

    @Override
    public void run() {
        try {
            do {
                setTarget(getLastCombatLogFile());
                final WatchKey key = watchService.take();
                key.pollEvents();
                key.reset();
            } while (run);
        } catch (IOException | ClosedWatchServiceException | InterruptedException e) {
            System.out.println("Task stopped on CombatLog folder -> " + folderPath);
        }
    }

    private Path getLastCombatLogFile() throws IOException {
        final File[] combatLogs = folderPath.toFile().listFiles();
        if (combatLogs == null || combatLogs.length == 0)
            throw new IOException("CombatLogs folder (" + folderPath.getFileName() + ") is empty.");

        Comparator<File> fileComparator = (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified());
        return Arrays.stream(combatLogs)
                .sorted(fileComparator)
                .map(File::toPath)
                .filter(this::isCombatLog)
                .findFirst().orElseThrow(() -> new IOException("CombatLogs folder (" + folderPath.getFileName() + ") does not contain any valid combat log files."));
    }

    private void setTarget(final Path newCombatLogPath) throws IOException {
        assert newCombatLogPath != null;
        if (newCombatLogPath.equals(filePath))
            return;

        System.out.println("TARGET HAS BEEN CHANGED -> " + newCombatLogPath.getFileName());
        filePath = newCombatLogPath;
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
        task = new FileWatcherTask(filePath, observer);
        threadWatcher = new Thread(task);
        threadWatcher.start();
    }

    private boolean isCombatLog(Path path) {
        return COMBAT_LOG_NAME_PATTERN.matcher(path.getFileName().toString()).matches();
    }

    void doOnClose() {
        run = false;
        try {
            closeStream();
            watchService.close();
        } catch (IOException | ClosedWatchServiceException ignored) {
        }
        System.out.println("The Folder Watcher has stopped reading the CombatLog folder -> " + folderPath);
    }
}
