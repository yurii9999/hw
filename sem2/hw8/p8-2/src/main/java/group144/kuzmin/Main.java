package group144.kuzmin;

import java.util.Random;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        final Random random = new Random();
        final Supplier<Integer> supplier = () -> random.nextInt(100) * random.nextInt(300000) / (random.nextInt(1000) + 10);
        final Lazy<Integer> commonLazy = new SingleThreadLazy(supplier);
        Runnable r = () -> System.out.println(commonLazy.get() + " i'm " + Thread.currentThread().getName());

        int amount = 2;
        Thread[] threads = new Thread[amount];
        for (int i = 0; i < amount; i++)
            threads[i] = new Thread(r);

        for (int i = 0; i < amount; i++)
            threads[i].start();
    }
}
