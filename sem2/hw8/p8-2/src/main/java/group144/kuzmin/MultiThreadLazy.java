package group144.kuzmin;

import java.util.function.Supplier;

public class MultiThreadLazy<T> implements Lazy<T> {
    private Supplier<T> supplier;
    private T result;

    public MultiThreadLazy(Supplier<T> supplier) {
        this.supplier = supplier;
        result = null;
    }

    @Override
    public T get() {
        if (supplier == null)
            return result;

        synchronized (this) {
            if (supplier == null)
                return result;

            result = supplier.get();
            supplier = null;
            return result;
        }
    }
}
