package sp;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths
                .stream()
                .filter(string -> string.contains(sequence))
                .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        final int AMOUNT_SHOTS = 10000;
        final Random random = new Random();

        long amountHits = IntStream.range(0, AMOUNT_SHOTS)
                .filter(a -> {
                    Double x = random.nextDouble();
                    Double y = random.nextDouble();

                    return x * x + y * y <= 1;
                })
                .count();

        return Double.valueOf(amountHits) / AMOUNT_SHOTS;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(el -> el
                        .getValue()
                        .size()))
                .map(el -> el.getKey())
                .orElse("");
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders
                .stream()
                .flatMap(order -> order
                        .entrySet()
                        .stream())
                .collect(Collectors.groupingBy(product -> product.getKey(), Collectors.summingInt(product -> (int)product.getValue())));
    }
}