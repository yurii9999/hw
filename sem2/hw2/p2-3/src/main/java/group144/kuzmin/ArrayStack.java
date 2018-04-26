package group144.kuzmin;

import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {
    private final int FIRST_ARRAY_SIZE = 20;
    private T[] data;
    private int pointer;
    private int nowArraySize;

    public ArrayStack() {
        nowArraySize = FIRST_ARRAY_SIZE;
        data = (T[]) new Object[FIRST_ARRAY_SIZE];
        pointer = 0;
    }

    @Override
    public void push(T value) {
        data[pointer] = value;
        pointer++;
        if (pointer == nowArraySize)
            increaseSize();
    }

    @Override
    public T pop() throws EmptyStackException {
        if (pointer == 0)
            throw new EmptyStackException();

        pointer--;
        T result = data[pointer];
        data[pointer] = null;
        if (pointer < nowArraySize / 2 && nowArraySize > FIRST_ARRAY_SIZE)
            reduceSize();

        return result;
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    private void increaseSize() {
        T[] oldData = data;
        data = (T[]) new Object[nowArraySize * 2];
        for (int i = 0; i < oldData.length; i++)
            data[i] = oldData[i];
    }

    private void reduceSize() {
        T[] oldData = data;
        data = (T[]) new Object[nowArraySize / 2];
        for (int i = 0; i < oldData.length; i++)
            data[i] = oldData[i];
    }
}
