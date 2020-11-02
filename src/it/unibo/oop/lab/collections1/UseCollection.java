package it.unibo.oop.lab.collections1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Example class using {@link java.util.List} and {@link java.util.Map}.
 * 
 */
public final class UseCollection {

	private static final int TIMES = 1_000;
	private static final int ELEMS = 100_000;
    private static final int TO_MS = 1_000_000;
	
    private UseCollection() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
    	final var array = new ArrayList<Integer>();
    	for(int i=1_000; i<2_000; i++) {
    		array.add(i);
    	}
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
    	var array2 = new LinkedList<Integer>(array);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
    	int temp = array2.get(array2.size() - 1);
    	array2.set(array2.size() - 1, array2.getFirst());
    	array2.set(0, temp);
    	
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
    	for(int a: array) {
    		System.out.print(a + " ");
    	}
    	System.out.println();
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
    	long time = System.nanoTime();
    	
    	for(int i=0; i<ELEMS; i++) {
    		array.add(i);
    	}
    	
    	time = System.nanoTime() - time;
        System.out.println("Adding " + ELEMS
                + " int in a ArrayList took " + time
                + "ns (" + time / TO_MS + "ms)");
        
        time = System.nanoTime();
    	
    	for(int i=0; i<ELEMS; i++) {
    		array2.add(i);
    	}
    	
    	time = System.nanoTime() - time;
        System.out.println("Adding " + ELEMS
                + " int in a LikedList took " + time
                + "ns (" + time / TO_MS + "ms)");
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        int pos = array.size() / 2;
        
        time = System.nanoTime();
        
        for(int i=0; i<TIMES; i++) {
        	array.get(pos);
        }
        
    	time = System.nanoTime() - time;
    	System.out.println("Reading " + TIMES
                + " int in a ArrayList took " + time
                + "ns (" + time / TO_MS + "ms)");
    	
    	pos = array2.size() / 2;
        
        time = System.nanoTime();
        
        for(int i=0; i<TIMES; i++) {
        	array2.get(pos);
        }
        
    	time = System.nanoTime() - time;
    	System.out.println("Reading " + TIMES
                + " int in a LikedList took " + time
                + "ns (" + time / TO_MS + "ms)");
    	
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
    	final Map<String,Long> continents = new HashMap<>();
    	continents.put("Africa",1_110_635_000L);
    	continents.put("Americas",972_005_000L);
    	continents.put("Antartica",0L);
    	continents.put("Asia",4_298_723_000L);
    	continents.put("Europe",742_452_000L);
    	continents.put("Oceania",38_304_000L);
        /*
         * 8) Compute the population of the world
         */
    	long population = 0;
    	
    	for(long l:continents.values()) {
    		population += l;
    	}
    	
    	System.out.println("Current world population is " + population);
    }
}
