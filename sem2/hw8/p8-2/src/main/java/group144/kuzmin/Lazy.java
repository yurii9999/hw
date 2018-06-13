package group144.kuzmin;

public interface Lazy<T> {
    /**
     * Method do lazy calculating or gives result if it did it in the past
     *
     * @return result of calculating
     */
    T get();
}
