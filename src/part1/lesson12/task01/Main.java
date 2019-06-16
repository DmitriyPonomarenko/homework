package part1.lesson12.task01;

import java.util.ArrayList;
import java.util.List;

/**
 * код, генерирующий ошибку java.lang.OutOfMemoryError: Java heap space
 * при VM options -Xmx1024m
 */
public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 1;
        while (true) {
            String element = String.valueOf(i);
            if (i++ % 2 == 0) {
                list.add(element);
            }
        }
    }
}