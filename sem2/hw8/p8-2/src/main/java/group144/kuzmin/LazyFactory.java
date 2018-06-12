package group144.kuzmin;

import java.util.function.Supplier;

public class LazyFactory {
    /**
     * Method creates singleThreadLazy without synchronize
     *
     * @param supplier supplier that you need to calculate
     * @param <T> Type of result
     * @return result of calculation
     */
    public static <T> Lazy<T> createSingleThreadLazy(Supplier<T> supplier) {
        return new SingleThreadLazy(supplier);
    }

    /**
     * Method creates synchronized multiThreadLazy
     *
     * @param supplier supplier that you need to calculate
     * @param <T> Type of result
     * @return result of calculation
     */
    public static <T> Lazy<T> createMultiThreadLazy(Supplier<T> supplier) {
        return new MultiThreadLazy(supplier);
    }
}
