package com.java.fp.study;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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
}