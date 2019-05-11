package part1.lesson06.task02.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * генератор текстовых файлов
 */
public class TextFileGenerator {

    private static final String LOWERS = "abcdefghigklmnopqrstuvwxyz";
    private static final String END_SENTENCE = ".!?";

    /**
     * генератор текстовых файлов
     *
     * @param path        каталог
     * @param n           количество файлов
     * @param size        размер файла
     * @param words       массив слов
     * @param probability вероятность
     * @throws IOException не удалось создать каталог
     */
    public static void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) if (!dir.mkdirs()) throw new IOException("Can't create path " + path);
        for (int i = 1; i <= n; i++) {
            generateFile(path + File.separator + "file" + i + ".txt", size, words, probability);
        }
    }

    /**
     * генератор текстового файла
     *
     * @param fileName    имя файла
     * @param size        размер файла
     * @param words       массив слов
     * @param probability вероятность
     * @throws FileNotFoundException не удалось создать файл
     */
    private static void generateFile(String fileName, int size, String[] words, int probability) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            int writed = 0;
            while (writed < size) {
                writed += writeParagraph(writer, size - writed, words, probability);
            }
        }
    }

    /**
     * генератор абзаца
     *
     * @param writer      PrintWriter
     * @param size        оставшийся размер
     * @param words       массив слов
     * @param probability вероятность
     * @return реальный размер
     */
    private static int writeParagraph(PrintWriter writer, int size, String[] words, int probability) {
        int sentences = (int) (Math.random() * 20) + 1;
        int writed = 2;
        if (size < 4) writed = 0;
        while (writed < size && sentences > 0) {
            String sentence = generateSentence(size - writed, words, probability);
            int remainder = size - writed - sentence.length();
            if (remainder > 0) {
                if (sentences == 1) {
                    if (remainder < 2) continue;
                } else {
                    if (remainder < 3) continue;
                    sentence = sentence + " ";
                }
            }
            writed += sentence.length();
            sentences--;
            writer.write(sentence);
        }
        if (size >= 4) writer.write("\n\r");
        return writed;
    }

    /**
     * генератор предложения
     *
     * @param size        оставшийся размер
     * @param words       массив слов
     * @param probability вероятность
     * @return сгенерированное предложение
     */
    private static String generateSentence(int size, String[] words, int probability) {
        int wordCount = (int) (Math.random() * 15) + 1;
        int writed = 1;
        List<String> list = new LinkedList<>();
        boolean usedPropability = false;
        if (Math.random() * probability < 1) {
            usedPropability = true;
        }
        while (writed < size && wordCount > 0) {
            String word;
            if (usedPropability) {
                word = words[(int) (Math.random() * words.length)];
            } else {
                word = generateWord(size - writed);
            }
            int remainder = size - writed - word.length();
            if (remainder < 0) continue;
            if (remainder > 0) {
                if (wordCount > 1) {
                    if (remainder < 2) continue;
                    if (remainder > 2) {
                        if (Math.random() * 15 < 1) {
                            word = word + ",";
                        }
                    }
                    word = word + " ";
                }
            }
            list.add(word);
            writed += word.length();
            wordCount--;
            usedPropability = false;
        }
        StringBuilder sb = new StringBuilder();
        while (list.size() > 1) {
            int random = (int) (Math.random() * (list.size() - 1));
            sb.append(list.get(random));
            list.remove(random);
        }
        sb.append(list.get(0));
        sb.append(END_SENTENCE.charAt((int) (Math.random() * END_SENTENCE.length())));
        String sentence = sb.toString();
        sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
        return sentence;
    }

    /**
     * генератор слова
     *
     * @param size оставшийся размер
     * @return сгенерированное слово
     */
    private static String generateWord(int size) {
        int wordLength = (int) (Math.random() * 15) + 1;
        if (wordLength > size) wordLength = size;
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= wordLength; i++) {
            result.append(LOWERS.charAt((int) (Math.random() * LOWERS.length())));
        }
        return result.toString();
    }
}
