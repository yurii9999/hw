package group144.kuzmin;

import java.util.EmptyStackException;

public interface Stack<T> {
    /**
     * Method adds value on the top of stack
     * @param value value you want to add
     */
    void push(T value);

    /**
     * Method returns value from the top and remove it
     * @return value that was on the top
     * @throws EmptyStackException throws when stack is empty
     */
    T pop() throws EmptyStackException;

    /**
     * Method check is stack empty
     * @return true when stack is empty and false otherwise
     */
    boolean isEmpty();
}
