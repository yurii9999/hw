package group144.kuzmin;

import java.util.function.Supplier;

public class SingleThreadLazy<T> implements Lazy<T> {
    private Supplier<T> supplier;
    private T result;

    public SingleThreadLazy(Supplier<T> supplier) {
        this.supplier = supplier;
        result = null;
    }

    @Override
    public T get() {
        if (supplier == null)
            return result;

        result = supplier.get();
        supplier = null;
        return result;
    }
}
