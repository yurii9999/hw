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
    public void add(T value, int position) {
        if (isIncluded(value))
            throw new AlreadyIncludedException();

        super.add(value, position);
    }

    /**
     * Method deletes element and throws Exception if list does not include it
     *
     * @param element - Method deletes element is equal this
     * @throws NotFoundException if element is not included to list
     */
    @Override
    public void delete(T element) {
        int oldSize = size();
        super.delete(element);

        if (oldSize == size())
            throw new NotFoundException();
    }
}

class AlreadyIncludedException extends RuntimeException { }

class NotFoundException extends RuntimeException { }
