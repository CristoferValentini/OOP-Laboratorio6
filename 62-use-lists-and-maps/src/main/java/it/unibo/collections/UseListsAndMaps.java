package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int ELEMS = 100_000;
    private static final int TIMES = 1_000;

    private UseListsAndMaps() {
    }

    /**
     * Program entry point.
     */
    public static void main() {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        List<Integer> firstList = new ArrayList<>();
        for (int i = 1000; i < 2000; i++) {
            firstList.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        List<Integer> secondList = new LinkedList<>(firstList);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        int dim = firstList.size();
        int temp = firstList.get(dim - 1);
        firstList.set(dim - 1, firstList.get(0));
        firstList.set(0, temp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        IO.print("|");
        for (Integer element : firstList) {
            IO.print(" " + element + " |");
        }
        IO.println();
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long headArrayListTime = System.nanoTime();
        for (int i = 0; i < ELEMS; i++) {
            firstList.add(0, i);
        }
        headArrayListTime = System.nanoTime() - headArrayListTime;

        long headLinkedListTime = System.nanoTime();
        for (int i = 0; i < ELEMS; i++) {
            secondList.addFirst(i);
        }
        headLinkedListTime = System.nanoTime() - headLinkedListTime;

        final var headArrayListMillis = TimeUnit.NANOSECONDS.toMillis(headArrayListTime);
        IO.println("Inserting " + ELEMS + " ints in the head of an ArrayList took "
                + headArrayListTime + "ns (" + headArrayListMillis + "ms)");

        final var headLinkedListMillis = TimeUnit.NANOSECONDS.toMillis(headLinkedListTime);
        IO.println("Inserting " + ELEMS + " ints in the head of a LinkedList took "
                + headLinkedListTime + "ns (" + headLinkedListMillis + "ms)");
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        long middleArrayListTime = System.nanoTime();
        for (int i = 0; i < TIMES; i++) {
            firstList.get(firstList.size() / 2);
        }
        middleArrayListTime = System.nanoTime() - middleArrayListTime;

        long middleLinkedListTime = System.nanoTime();
        for (int i = 0; i < TIMES; i++) {
            secondList.get(secondList.size() / 2);
        }
        middleLinkedListTime = System.nanoTime() - middleLinkedListTime;

        final var middleArrayListMillis = TimeUnit.NANOSECONDS.toMillis(middleArrayListTime);
        IO.println("Reading " + TIMES + " ints in the middle of an ArrayList took "
                + middleArrayListTime + "ns (" + middleArrayListMillis + "ms)");

        final var middleLinkedListMillis = TimeUnit.NANOSECONDS.toMillis(middleLinkedListTime);
        IO.println("Reading " + TIMES + " ints in the middle of a LinkedList took "
                + middleLinkedListTime + "ns (" + middleLinkedListMillis + "ms)");
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> newMap = new HashMap<>();
        newMap.put("Africa", 1_110_635_000L);
        newMap.put("Americas", 972_005_000L);
        newMap.put("Antarctica", 0L);
        newMap.put("Asia", 4_298_723_000L);
        newMap.put("Europe", 742_452_000L);
        newMap.put("Oceania", 38_304_000L);
        /*
         * 8) Compute the population of the world
         */
        long worldPopulation = 0;
        for (Long values : newMap.values()) {
            worldPopulation += values;
        }
        IO.println("The world population is: " + worldPopulation);
    }
}
