package group144.kuzmin;

import org.junit.Test;

import java.util.Random;
import java.util.Stack;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class LazyFactoryTest {
    @Test
    public void randomSupplierTest() throws InterruptedException {
        Random random = new Random();
        Lazy<Integer> lazy = LazyFactory.createMultiThreadLazy(() -> random.nextInt(1000));
        int[] results = new int[2];
        Thread thread = new Thread(() -> results[0] = lazy.get());
        thread.start();
        results[1] = lazy.get();
        thread.join();
        assertEquals(results[0], results[1]);
    }

    @Test
    public void singleThreadLazyRandom() {
        Random random = new Random();
        Lazy<Integer> lazy = LazyFactory.createSingleThreadLazy(() -> random.nextInt());
        assertEquals(lazy.get(), lazy.get());
    }

    @Test
    public void simpleSingleThreadLazyTest() {
        Supplier<String> supplier = () -> "M(";
        Lazy<String> lazy = LazyFactory.createSingleThreadLazy(supplier);
        assertEquals("M(", lazy.get());
    }

    @Test
    public void simpleMultiThreadLazyTest() {
        Supplier<String> supplier = () -> "M(";
        Lazy<String> lazy = LazyFactory.createMultiThreadLazy(supplier);
        assertEquals("M(", lazy.get());
    }

    @Test
    public void calculatingSingleThreadLazyGivesDifferentResultsTest() {
        final int AMOUNT = 100000;
        final Random RANDOM = new Random();
        final Double[] results = new Double[2];

        Supplier<Double> calculatePI = () -> Double.valueOf(IntStream
                .range(1, AMOUNT)
                .filter(a -> {
                    Double x = RANDOM.nextDouble();
                    Double y = RANDOM.nextDouble();
                    return x * x + y * y < 1; })
                .count()) / AMOUNT * 4;

        Lazy<Double> lazy = LazyFactory.createSingleThreadLazy(calculatePI);
        Runnable runnable = () -> results[0] = lazy.get();
        Thread thread = new Thread(runnable);

        thread.start();
        results[1] = lazy.get();
        assertEquals(results[0], results[1], 1);
    }

    @Test
    public void calculatingMultiThreadsLazyGivesCommonResult() throws InterruptedException {
        final int AMOUNT = 100000;
        final Random RANDOM = new Random();
        final Double[] results = new Double[2];

        Supplier<Double> calculatePI = () -> Double.valueOf(IntStream
                .range(1, AMOUNT)
                .filter(a -> {
                    Double x = RANDOM.nextDouble();
                    Double y = RANDOM.nextDouble();
                    return x * x + y * y < 1; })
                .count()) / AMOUNT * 4;

        Lazy<Double> lazy = LazyFactory.createMultiThreadLazy(calculatePI);
        Runnable runnable = () -> results[0] = lazy.get();
        Thread thread = new Thread(runnable);

        thread.start();
        results[1] = lazy.get();
        thread.join();
        assertEquals(results[0], results[1]);
    }

    @Test
    public void supplierGetsResultsFromArrayTest() throws InterruptedException {
        Stack<String> store = new Stack();
        store.push("Useful item");
        store.push("Useless item");
        String[] results = new String[2];

        Supplier<String> supplier = () -> store.pop();
        Lazy<String> lazy = LazyFactory.createMultiThreadLazy(supplier);
        Thread thread = new Thread(() -> results[0] = lazy.get());
        thread.start();
        results[1] = lazy.get();
        thread.join();
        assertEquals(results[0], results[1]);
    }
}