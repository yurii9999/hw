package group144.kuzmin;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

public class ListStack<T> implements Stack<T> {
    private List<T> data;

    public ListStack() {
        data = new LinkedList();
    }

    /**
     * Method adds value on the top of stack
     * @param value value you want to add
     */
    @Override
    public void push(T value) {
        data.add(value);
    }

    /**
     * Method returns value from the top and remove it
     * @return value that was on the top
     * @throws EmptyStackException throws when stack is empty
     */
    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();

        T result = data.get(data.size() - 1);
        data.remove(data.size() - 1);
        return result;
    }

    /**
     * Method check is stack empty
     * @return true when stack is empty and false otherwise
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
