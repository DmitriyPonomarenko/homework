package part1.lesson12.task02;

import part1.lesson09.task01.classloader.MyClassLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * код, генерирующий ошибку java.lang.OutOfMemoryError: Metaspace
 * при VM options -XX:MaxMetaspaceSize=512m
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        List<Class> list = new ArrayList<>();
        while (true) {
            ClassLoader cl = new MyClassLoader();
            Class<?> someClass = cl.loadClass("part1.lesson09.task01.worker.SomeClass");
            list.add(someClass);
        }
    }

}