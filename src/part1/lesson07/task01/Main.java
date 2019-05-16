package part1.lesson07.task01;

import part1.lesson07.task01.util.Factorial;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * программа для вычисления факториалов элементов массива
 * использован пул потоков для решения задачи
 */
public class Main {

    private static final int N = 1000;
    private static final int MAX_VALUE = 1000;
    private static final int NUMBER_OF_THREADS = 2;

    private static Factorial factorial = new Factorial();

    private static int[] numbers = new int[N];

    static {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * MAX_VALUE) + 1;
        }
    }

    private static BigInteger[] factorials = new BigInteger[N];

    public static void main(String[] args) {

        long start = System.nanoTime();
        ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        int minIndex = 0;
        for (int n = 0; n < NUMBER_OF_THREADS; n++) {
            int maxIndex = Math.round(N / NUMBER_OF_THREADS * (n + 1));
            threadPool.execute(
                    new SingleThreadComputer(minIndex, maxIndex));
            minIndex = maxIndex;

        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return;
        }
        long duration = System.nanoTime() - start;
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i] + "! = " + factorials[i]);
        }
        System.out.println("Время выполнения = " + duration / 1000000 + " мс");
    }

    /**
     * поток для вычисления факториалов части массива
     */
    private static class SingleThreadComputer implements Runnable {

        private int minIndex;
        private int maxIndex;

        SingleThreadComputer(int minIndex, int maxIndex) {
            this.minIndex = minIndex;
            this.maxIndex = maxIndex;
        }

        @Override
        public void run() {
            for (int i = minIndex; i < maxIndex; i++) {
                factorials[i] = factorial.compute(numbers[i]);
            }
        }
    }
}
