package group144.kuzmin;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MultithreadSorter {
    /**
     * Method gets array and sorts it
     *
     * @param array array need to sort
     * @param comparator how to compare T's
     * @param <T> type of array's elements
     */
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new SortPart(array, comparator, 0, array.length - 1));
    }

    private static class SortPart<T> extends RecursiveAction {
        private int from;
        private int to;
        private T[] array;
        private Comparator<T> comparator;

        public SortPart(T[] array, Comparator<T> comparator, int from, int to) {
            this.from = from;
            this.to = to;
            this.array = array;
            this.comparator = comparator;
        }

        @Override
        protected void compute() {
            if (to - from < 1)
                return;

            T pivot = array[from];
            int i = from;
            int j = to;

            while (i <= j) {
                while (comparator.compare(array[i], pivot) < 0)
                    i++;

                while (comparator.compare(array[j], pivot) > 0)
                    j--;

                if (i <= j) {
                    swap(array, i, j);
                    i++;
                    j--;
                }
            }



            if (i - 1 - from > 50000 && to - i > 50000) {
                SortPart left = new SortPart(array, comparator, from, i - 1);
                SortPart right = new SortPart(array, comparator, i, to);
                right.fork();
                left.compute();
                right.join();
            } else {
                int tempTo = to;
                to = i - 1;
                compute();
                from = i;
                to = tempTo;
                compute();
            }
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
