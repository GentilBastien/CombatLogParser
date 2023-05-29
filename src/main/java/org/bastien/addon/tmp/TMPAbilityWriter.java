package org.bastien.addon.tmp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class TMPAbilityWriter {
    private final Set<String> lines;

    private TMPAbilityWriter() {
        this.lines = new HashSet<>();
        Path folderPath = Path.of("D:\\Users\\basti\\Documents\\Star Wars - The Old Republic\\CombatLogs\\ANSI");
        Path newFolderPath = Path.of("D:\\Users\\basti\\Documents\\Star Wars - The Old Republic\\CombatLogs\\UTF8");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
            System.out.println("Processing...");
            for (Path path : stream) {
                Path newPath = Path.of(newFolderPath.toString(), path.getFileName().toString());
                changeANSItoUTF8(path, newPath);
//                for (String s : Files.readAllLines(path))
//                    readCombatLogLine(s);
            }
            System.out.println("Writing...");
            writeInAbilityFile();
            System.out.println("Completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TMPAbilityWriter();
    }

    public void readCombatLogLine(String line) throws IOException, InterruptedException {
        String[] sources = line.split("(?<=])\\s");
        String source = null;
        try {
            source = sources[3];
        } catch (Exception e) {
            System.err.println("***************");
            System.err.println("EXCEPTION AT THIS LINE -> " + line);
            Thread.sleep(5000);
        }
        this.lines.add(source);
    }

    private void writeInAbilityFile() throws IOException {
        Path fileWriterPath = Path.of("D:\\Users\\basti\\Desktop\\abilities.txt");
        final BufferedWriter writer = new BufferedWriter(new FileWriter(fileWriterPath.toFile()));
        for (String str : this.lines) {
            System.out.println(str);
            writer.write(str);
            writer.newLine();
        }
        writer.close();
    }

    public void changeANSItoUTF8(Path ansiFilePath, Path utf8FilePath) {
        try {
            // Create an InputStreamReader to read the ANSI file
            InputStreamReader isr = new InputStreamReader(new FileInputStream(ansiFilePath.toFile()), "Cp1252");

            // Create a BufferedWriter to write the UTF-8 file
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(utf8FilePath.toFile()), StandardCharsets.UTF_8));

            // Read and write the file line by line
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            bufferedReader.close();
            writer.close();
            System.out.println("ANSI file encoded and saved as UTF-8 successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
