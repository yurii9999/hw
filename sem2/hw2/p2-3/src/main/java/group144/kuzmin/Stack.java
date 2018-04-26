package group144.kuzmin;

import java.util.EmptyStackException;

public interface Stack<T> {
    void push(T value);
    T pop() throws EmptyStackException;
    boolean isEmpty();
}
