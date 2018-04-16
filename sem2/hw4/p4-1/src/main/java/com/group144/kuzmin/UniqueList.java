package com.group144.kuzmin;

public class UniqueList<T> extends List<T> {
    /**
     * Method adds elements and throws Exception if list already include equal element
     *
     * @param value - element which you want to add
     * @param position - position that you want to add it to
     * @throws AlreadyIncludedException if element already added to list
     */
    @Override
    public void add(T value, int position) throws AlreadyIncludedException {
        if (isIncluded(value))
            throw new AlreadyIncludedException();

        super.add(value, position);
    }
}