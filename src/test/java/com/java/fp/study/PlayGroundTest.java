package com.java.fp.study;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
}