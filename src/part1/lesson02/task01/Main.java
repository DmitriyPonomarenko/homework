package part1.lesson02.task01;

import part1.lesson02.task01.customExceptions.SampleException;

/**
 * Пример на Exception
 */
public class Main {

    public static void main(String[] args) throws SampleException {
        String outString = "Hello, World!";
        outString(outString);
        try {
            String string = null;
            outString(string);
        } catch (NullPointerException npe) {
            try {
                char[] charArray = outString.toCharArray();
                for (int i = 0; i <= outString.length(); i++) {
                    System.out.print(charArray[i]);
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                SampleException sampleException = new SampleException("Произошла ошибка");
                sampleException.addSuppressed(aioobe);
                sampleException.addSuppressed(npe);
                throw sampleException;
            }
        }
    }

    /**
     * Выводит строку и ее длину
     *
     * @param string строка
     */
    private static void outString(String string) {
        System.out.println(string + " (length = " + string.length() + ")");
    }
}
