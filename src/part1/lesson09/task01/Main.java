package part1.lesson09.task01;

import part1.lesson09.task01.classloader.MyClassLoader;
import part1.lesson09.task01.worker.Worker;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * пример на кастомный ClassLoader
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter("SomeClass.java")) {
            stringOut(pw, "package part1.lesson09.task01.worker;");
            stringOut(pw, "");
            stringOut(pw, "public class SomeClass implements Worker {");
            stringOut(pw, "");
            stringOut(pw, "    @Override");
            stringOut(pw, "    public void doWork() {");

            Scanner scanner = new Scanner(System.in);
            String line;
            while ((line = stringIn(scanner)) != null) {
                pw.println(line);
            }

            stringOut(pw, "    }");
            stringOut(pw, "}");
            stringOut(pw, "");
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler.run(null, null, null, "SomeClass.java") != 0) {
            return;
        }

        ClassLoader cl = new MyClassLoader();
        try {
            Class<?> someClass = cl.loadClass("part1.lesson09.task01.worker.SomeClass");
            Worker worker = (Worker) someClass.newInstance();
            worker.doWork();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void stringOut(PrintWriter pw, String line) {
        System.out.println(line);
        pw.println(line);
    }

    private static String stringIn(Scanner scanner) {
        String tab = "        ";
        System.out.print(tab);
        String line = scanner.nextLine();
        if (line.equals("")) return null;
        return tab + line;
    }
}
