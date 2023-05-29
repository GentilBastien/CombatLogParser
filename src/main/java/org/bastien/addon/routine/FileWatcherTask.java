package org.bastien.addon.routine;

import io.reactivex.rxjava3.core.Observable;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class FileWatcherTask implements Runnable {
    private final Path filePath;
    private final LogObserver observer;
    private final Observable<String> observable;
    private RandomAccessFile raf;

    public FileWatcherTask(final Path filePath, final LogObserver observer) {
        this.filePath = filePath;
        this.observer = observer;
        this.observable = Observable.interval(1500, TimeUnit.MILLISECONDS)
                .map(tick -> poolNewLines())
                .flatMap(Observable::fromIterable);
        try {
            this.raf = new RandomAccessFile(filePath.toFile(), "r");
            markEndFile();
        } catch (IOException e) {
            System.err.println("I/O Exception has occurred in the FileWatcherTask.");
            doOnClose();
        }
    }

    private static boolean isValidCombatLog(final String log) {
        if (log.length() < 10)
            return false;
        String[] split = log.split("(?<=])\\s(?=[\\[(<])");
        if (split.length < 5 || split.length > 6)
            return false;
        for (int i = 0; i < 5; i++)
            if (!split[i].startsWith("[") || !split[i].endsWith("]"))
                return false;
        char lastChar = log.charAt(log.length() - 1);
        return lastChar == ']' || lastChar == ')' || lastChar == '>';
    }

    @Override
    public void run() {
        observable.subscribe(observer);
    }

    private void markEndFile() throws IOException {
        String line;
        do {
            long currentFilePosition = raf.getFilePointer();
            line = raf.readLine();
            if (line == null) return;
            if (!isValidCombatLog(line)) {
                raf.seek(currentFilePosition);
                return;
            }
        } while (true);
    }

    private List<String> poolNewLines() throws IOException {
        List<String> list = new ArrayList<>();
        String line;
        do {
            long currentFilePosition = raf.getFilePointer();
            line = raf.readLine();
            if (line == null) return list;
            if (isValidCombatLog(line)) {
                list.add(line);
            } else {
                System.err.println("Invalid line -> " + line);
                raf.seek(currentFilePosition);
                return list;
            }
        } while (true);
    }

    void doOnClose() {
        try {
            observer.onComplete();
            raf.close();
        } catch (IOException ignored) {
        }
        System.out.println("The File Watcher has stopped reading the combatLog -> " + filePath.getFileName());
    }
}
