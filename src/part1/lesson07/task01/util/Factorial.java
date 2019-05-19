package part1.lesson07.task01.util;

import java.math.BigInteger;
import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * потокобезопасный класс для вычисления факториала числа
 * запоминает ранее вычисленные значения для быстрого вычисления новых значений
 */
public class Factorial {

    private NavigableMap<Integer, BigInteger> computedValues = new ConcurrentSkipListMap<>();

    /**
     * вычисляет факториал числа
     *
     * @param number исходное число
     * @return факториал исходного числа
     */
    public BigInteger compute(int number) {
        BigInteger result;
        int n;
        Map.Entry<Integer, BigInteger> floorEntry = computedValues.floorEntry(number);
        if (floorEntry != null) {
            result = floorEntry.getValue();
            n = floorEntry.getKey();
            if (n == number) {
                return result;
            }
        } else {
            result = BigInteger.ONE;
            n = 1;
        }
        while (n < number) {
            n++;
            result = result.multiply(BigInteger.valueOf(n));
        }
        computedValues.put(n, result);
        return result;
    }
}
