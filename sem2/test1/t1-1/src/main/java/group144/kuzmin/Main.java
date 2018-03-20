package group144.kuzmin;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.enqueue(3, 4);
        queue.enqueue(6, 10);
        queue.enqueue(30, 2);
        queue.enqueue(15, 6);
        System.out.println(queue);

        try {
            for (int i = 0; i < 2; i++)
                System.out.println(queue.dequeue());
        } catch (EmptyQueueException e) {
            System.out.println("it's empty(");
        }

        System.out.println(queue);

        try {
            for (int i = 0; i < 10; i++)
                System.out.println(queue.dequeue());
        } catch (EmptyQueueException e) {
            System.out.println("it's empty(");
        }

        System.out.println("is empty? : " + queue.isEmpty());
    }
}
