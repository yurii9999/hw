package group144.kuzmin;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

public class ListStack<T> implements Stack<T> {
    private List<T> data;

    public ListStack() {
        data = new LinkedList();
    }

    @Override
    public void push(T value) {
        data.add(value);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();

        T result = data.get(data.size() - 1);
        data.remove(data.size() - 1);
        return result;
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
