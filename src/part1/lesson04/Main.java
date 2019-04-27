package part1.lesson04;

import part1.lesson04.task03.MathBox;

public class Main {
    public static void main(String[] args) {
        Number[] array = {1, 2, 2, null, 2.5, 2.5};
        MathBox mathBox = new MathBox(array);
        mathBox.dump();
        mathBox.deleteObject(2);
        mathBox.dump();
        System.out.println(mathBox.summator());
        mathBox.splitter(3.5);
        mathBox.dump();
        System.out.println(mathBox.summator());
        mathBox.addObject((Number) new Object());
    }
}
