package org.bastien.addon.routine;

import io.reactivex.rxjava3.core.Observable;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class FileWatcherTask implements Runnable {
    private static final int MAX_WRONG_BEFORE_SKIP = 2;
    private static final int MAX_NEW_LINES_TO_PULL = 250;
    private final Path filePath;
    private final CombatLogObserver observer;
    private final Observable<String> observable;
    private RandomAccessFile raf;
    private int wrong = 0;

    public FileWatcherTask(final Path filePath, final CombatLogObserver observer) {
        this.filePath = filePath;
        this.observer = observer;
        this.observable = Observable.interval(5500, TimeUnit.MILLISECONDS)
                .map(tick -> poolNewLines())
                .flatMap(Observable::fromIterable);
        try {
            this.raf = new RandomAccessFile(filePath.toFile(), "r");
            markEndFile();
            System.out.println("-- Finished reading file --");
        } catch (IOException e) {
            System.err.println("I/O Exception has occurred in the FileWatcherTask.");
            doOnClose();
        }
    }

    private static boolean isValidCombatLog(final String log) {
        if (log.length() < 50) {
            System.err.println("Invalid line -> " + log + " -> length shorter than 50");
            return false;
        }

        String[] split = log.split("(?<=])\\s(?=[\\[(<])");
        if (split.length < 5 || split.length > 6) {
            System.err.println("Invalid line -> " + log + " -> less than 5 data or more than 6");
            return false;
        }

        for (int i = 0; i < 5; i++) {
            if (!split[i].startsWith("[") || !split[i].endsWith("]")) {
                System.err.println("Invalid line -> " + log + " -> data does not start or end by [ ]");
                return false;
            }
        }

        char lastChar = log.charAt(log.length() - 1);
        if (lastChar == ']' || lastChar == ')' || lastChar == '>')
            return true;

        System.err.println("Invalid line -> " + log + " -> line does not finish by ] ) or >");
        return false;
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
        int nbLinesRead = 0;
        do {
            long currentFilePosition = raf.getFilePointer();
            line = raf.readLine();
            if (line == null) return list;
            if (isValidCombatLog(line)) {
                this.wrong = 0;
                list.add(line);
                nbLinesRead++;
            } else {
                if (this.wrong >= MAX_WRONG_BEFORE_SKIP) {
                    this.wrong = 0;
                    raf.readLine();
                } else {
                    this.wrong++;
                    raf.seek(currentFilePosition);
                }
                return list;
            }
        } while (nbLinesRead < MAX_NEW_LINES_TO_PULL);
        return list;
    }

    void doOnClose() {
        try {
            observer.onComplete();
            raf.close();
        } catch (IOException ex) {
            System.err.println("Failed to close -> " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("The File Watcher has stopped reading the combatLog -> " + filePath.getFileName());
    }
}
