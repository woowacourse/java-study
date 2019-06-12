package techcourse.fp.study;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlayGroundTest {
    private List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void printAllOld() {
        PlayGround.printAllOld(numbers);
    }

    @Test
    public void printAllLambda() {
        PlayGround.printAllLambda(numbers);
    }

    @Test
    public void Function_예제() {
        println("Area is ", 2, 3, (message, length, width) -> message + (length * width));
    }

    @FunctionalInterface
    interface TriFunction<T1, T2, T3, R> {
        R apply(T1 t1, T2 t2, T3 t3);
    }

    private <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, TriFunction<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }

    @Test
    public void Predicate_예제() {
        Predicate<Integer> isPositive = i -> i > 0;
        Predicate<Integer> lessThanThree = i -> i < 3;
        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        List<Integer> filteredPositive = filter(numbers, isPositive);
        assertThat(filteredPositive.size()).isEqualTo(5);

        List<Integer> filteredLessThanThree = filter(numbers, lessThanThree);
        assertThat(filteredLessThanThree.size()).isEqualTo(8);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> condition) {
        return list.stream()
                .filter(condition)
                .collect(toList());
    }

    @Test
    public void Eager_Evaluation_테스트() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<String> result = new ArrayList<>();

        for (Integer number : numbers) {
            log("starting", number);
            log("filtering", number);

            if (number > 3) {
                log("post filtering", number);
                number = number * 2;

                String convertValue = "#" + number;
                log("post converting", number);

                result.add(convertValue);
                continue;
            }
        }

        log("Invoking terminal method count.\n");
        log("The count is", result.size());
    }

    @Test
    public void Lazy_Evaluation_테스트() {
        Stream stream = Stream.of(1, 2, 3, 4, 5)
                .peek(i -> log("starting", i))
                .filter(i -> {
                    log("filtering", i);
                    return i > 3;
                })
                .peek(i -> log("post filtering", i))
                .map(v -> v * 2)
                .map(i -> "#" + i)
                .peek(i -> log("post converting", i));

        log("Invoking terminal method count.\n");
        log("The count is", stream.count());
    }

    public static void log(Object... objects) {
        String now = LocalDateTime.now().toString();

        for (Object object : objects) {
            now += " - " + object.toString();
        }

        System.out.println(now);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}