package com.zipcodewilmington.arrayutility;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {
    private T[] array;

    public ArrayUtility(T[] array) {
        this.array = array;
    }

    public T[] removeValue(T valueToRemove) {
        return Stream.of(array).filter(element -> !element.equals(valueToRemove))
                .toArray(size -> Arrays.copyOf(array, size));
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        return (int) Stream.of(array).filter(element -> element.equals(valueToEvaluate)).count();
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        array = Stream.concat(Stream.of(array), Stream.of(arrayToMerge))
                .toArray(size -> Arrays.copyOf(array, size));

        return getNumberOfOccurrences(valueToEvaluate);
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        array = Stream.concat(Stream.of(array), Stream.of(arrayToMerge))
                .toArray(size -> Arrays.copyOf(array, size));

        return Stream.of(array).max((Comparator.comparingInt(this::getNumberOfOccurrences))).orElse(null);
    }
}
