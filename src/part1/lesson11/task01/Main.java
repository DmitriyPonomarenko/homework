package part1.lesson11.task01;

import part1.lesson11.task01.util.Factorial;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * программа для вычисления факториалов элементов массива
 * использованы лямбда-выражения и потоки данных
 */
public class Main {

    private static final int N = 1000;
    private static final int MAX_VALUE = 1000;

    public static void main(String[] args) {

        int[] numbers = new int[N];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * MAX_VALUE) + 1;
        }

        IntFunction<BigInteger> factorialFunc = new Factorial()::compute;

        long start = System.nanoTime();

        BigInteger[] factorials = Arrays.stream(numbers)
                .parallel()
                .mapToObj(factorialFunc)
                .toArray(BigInteger[]::new);

        long duration = System.nanoTime() - start;

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i] + "! = " + factorials[i]);
        }
        System.out.println("Время выполнения = " + duration / 1000000 + " мс");
    }

}
