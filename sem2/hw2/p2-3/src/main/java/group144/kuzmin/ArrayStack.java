package group144.kuzmin;

import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T>{
    private final int FIRST_ARRAY_SIZE = 20;
    private T[] data;
    private int pointer;
    private int nowArraySize;

    public ArrayStack() {
        nowArraySize = FIRST_ARRAY_SIZE;
        data = (T[]) new Object[FIRST_ARRAY_SIZE];
        pointer = 0;
    }

    /**
     * Method adds value on the top of stack
     * @param value value you want to add
     */
    @Override
    public void push(T value) {
        data[pointer] = value;
        pointer++;
        updateSize();
    }

    /**
     * Method returns value from the top and remove it
     * @return value that was on the top
     * @throws EmptyStackException throws when stack is empty
     */
    @Override
    public T pop() throws EmptyStackException {
        if (pointer == 0)
            throw new EmptyStackException();

        pointer--;
        T result = data[pointer];
        data[pointer] = null;
        updateSize();

        return result;
    }

    /**
     * Method check is stack empty
     * @return true when stack is empty and false otherwise
     */
    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    /** Method increase array size if it is small and reduce if it is large */
    private void updateSize() {
        if (pointer == nowArraySize) {
            // Increase size
            T[] oldData = data;
            data = (T[]) new Object[nowArraySize * 2];
            for (int i = 0; i < oldData.length; i++)
                data[i] = oldData[i];

            return;
        }


        if (pointer < nowArraySize / 2 && nowArraySize > FIRST_ARRAY_SIZE) {
            // Reduce size
            T[] oldData = data;
            data = (T[]) new Object[nowArraySize / 2];
            for (int i = 0; i < oldData.length; i++)
                data[i] = oldData[i];
        }
    }
}
