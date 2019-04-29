package part1.lesson06.task02;

import part1.lesson06.task02.util.TextFileGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * пример на синтез текстовых файлов
 */
public class Main {

    public static void main(String[] args) throws IOException {
        List<String> words = Files.readAllLines(new File("Out.txt").toPath());
        words.remove("");
        TextFileGenerator.getFiles(".", 10, 10000, words.toArray(new String[0]), 1);
    }
}
