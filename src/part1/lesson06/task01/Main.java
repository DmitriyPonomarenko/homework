package part1.lesson06.task01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.TreeSet;

/**
 * пример на анализ текстового файла
 */
public class Main {

    private static final String IN_FILE_NAME = "In.txt";
    private static final String OUT_FILE_NAME = "Out.txt";
    private static final String WORD_DELIMETER = " ";
    private static final String PUNCTUATION_MARKS = "[,.!?]";

    public static void main(String[] args) throws IOException {
        TreeSet<String> list = new TreeSet<>();
        List<String> lines = Files.readAllLines(new File(IN_FILE_NAME).toPath());
        for (String line : lines) {
            String[] words = line.split(WORD_DELIMETER);
            for (String word : words) {
                list.add(word.replaceAll(PUNCTUATION_MARKS, "").toLowerCase());
            }
        }
        try (PrintWriter writer = new PrintWriter(OUT_FILE_NAME)) {
            for (String word : list) {
                writer.println(word);
            }
        }
    }
}
