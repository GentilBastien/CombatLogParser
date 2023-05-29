package org.bastien.addon.tmp;

import org.bastien.addon.routine.FileWatcherTask;
import org.bastien.addon.routine.LogObserver;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ParseAll {
    public static void main(String[] args) {
        new ParseAll();
    }
    public ParseAll() {
        Path folderPath = Path.of("D:\\Users\\basti\\Documents\\Star Wars - The Old Republic\\CombatLogs");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
            LogObserver observer = new LogObserver();
            for (Path path : stream) {
                FileWatcherTask task = new FileWatcherTask(path, observer);
                Thread t = new Thread(task);
                t.start();
            }
            Thread.sleep(500_000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            //10758702
        }
    }
}
