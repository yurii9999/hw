package group144.kuzmin;

import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("String1111" , "String555" , "String33");
        stream.sorted(Comparator.comparing(String::length)).forEach(s -> System.out.println(s + ", "));

    }
}


