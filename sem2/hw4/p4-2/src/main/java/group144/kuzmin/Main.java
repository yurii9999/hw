package group144.kuzmin;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> a = new Set();
        Collection<Integer> b = new Set();

        for (int i = 1; i <= 11; i++ )
            a.add(i);

        for (int i = 4; i <= 20; i++)
            b.add(i);

        System.out.println("a: " + a + "\nb: " + b);

        Collection<Integer> aAndB = new Set();
        aAndB.addAll(a);
        aAndB.retainAll(b);
        System.out.println("aAndB: " + aAndB);

        Collection<Integer> aOrB = new Set();
        aOrB.addAll(a);
        aOrB.addAll(b);
        System.out.println("aOrB: " + aOrB);
    }
}
