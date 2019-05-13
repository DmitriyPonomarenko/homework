package part1.lesson09.task01.classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * кастомный загрузчик классов
 */
public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            return findClass(name);
        } catch (ClassNotFoundException e) {
            return super.loadClass(name);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            int lastDotPos = name.lastIndexOf('.');
            String simpleName = name.substring(lastDotPos + 1);
            byte[] bytes = Files.readAllBytes(Paths.get(simpleName + ".class"));
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            ClassNotFoundException ex = new ClassNotFoundException(name + ": класс не найден");
            ex.addSuppressed(e);
            throw ex;
        }
    }
}
