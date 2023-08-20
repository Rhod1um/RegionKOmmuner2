package com.example.regionkommuner2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//ingen annotering, behøver man ikke hvis det er simple tests. Så kan man dog heller ikke autowire
//kun annoter hvis man fx skal have hele spring startet.
public class Test1 {

    @Test
    public void testarr() {
        int[] numbers = {1, 2, 3, 8, 4};
        int[] expected = {1, 2, 3, 4, 8};

        Arrays.sort(numbers);
        //Assertions.assertEqual(expected, numbers);
        //assertEquals funktion ændre sig, der står version når man tager den ind
        //metode kalder == metode, pointer er ikke ens
        //derfr bruges denne:
        assertArrayEquals(expected, numbers, "Tester 5 tal ");
    }

    @Test
    public void testarrNot() {
        int[] numbers = {1, 2, 3, 8, 4};
        int[] expected = {1, 2, 3, 4, 8};

        Arrays.sort(numbers);
        Assertions.assertEquals(expected, numbers);
        //assertEqual(expected, numbers);
        //assertEquals funktion ændre sig, der står version når man tager den ind
        //metode kalder == metode, pointer er ikke ens
        //derfr bruges denne:
        //assertArrayEquals(expected, numbers, "Tester 5 tal ");

        assertNotEquals(numbers[0], expected[3], "Tester 5 tal ");
    }

    //her bruges til hvs man nu har 30 array sp gider man ikke skrive 30 tests så man laver
    //test der tager parametre
    @DisplayName("Test af sortering af tal") //virker ikke?
    @ParameterizedTest
    @MethodSource("sortedNumbersArgument")
    void testArrParm(int[] numbers, int[] expected) {
        Arrays.sort(numbers);
        assertArrayEquals(expected, numbers, "Sortering af " + numbers.length + " tal");
    }
    //husk skal være Arguments i flertal
    //erik: havde skrevet Argument og kunne ike finde fejlen, gav det til chatGPT
    //skal hedde det samme som i MethodSource
    private static Stream<Arguments> sortedNumbersArgument(){
        //funktionel programmering, meget på én linje:
        //toArray() gør at vi får en int[] hvilket skal være parameter til ovenover metode
        Arguments.of(IntStream.of(1,2,3).toArray(), IntStream.of(1,2,3).toArray());

        return Stream.of(
                Arguments.of(IntStream.of(1,2,3).toArray(), IntStream.of(1,2,3).toArray()),
                Arguments.of(IntStream.of(1,2,3).toArray(), IntStream.of(1,2,3).toArray()),
                Arguments.of(IntStream.of(1,2,3).toArray(), IntStream.of(1,2,3).toArray())
        ); //hvorfor virker sort ikke
    }
}
