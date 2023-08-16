package com.learnJava.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelStreamsDemo {

    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Using a parallel stream to sum the values
        int sum = numbers.parallelStream()
                         .mapToInt(Integer::intValue)
                         .sum();
        System.out.println("Sum of numbers using parallel stream1: " + sum);
        
        // Demonstrating non-deterministic order of execution
        System.out.println("\nDemonstrating non-deterministic order of execution:");
        
        
        /*
         We then demonstrate the non-deterministic order of execution by calling forEach() twice on the same parallel stream and observing that the order of the output is 
         different each time.
         */
        
        numbers.parallelStream()
               .forEach(System.out::print);
        
       System.out.println("**** PRINT 2 ****");
        
        numbers.parallelStream()
               .forEach(System.out::print);
     //   System.out.println();
        
        
        
        // Demonstrating thread-safety issues
        System.out.println("\nDemonstrating thread-safety issues:");
        List<Integer> sharedList = new ArrayList<>();
        
        /*
         Next, we demonstrate a thread-safety issue by attempting to add elements to a shared list within the parallel stream pipeline. Since multiple threads are 
         accessing the shared list concurrently, we may end up with unexpected results
         
         However, if we were modifying a shared collection or other shared mutable state in the stream pipeline, we would need to properly synchronize access to avoid race 
         conditions.
         */
        
        numbers.parallelStream()
               .map(n -> n * 2)
               .forEach(sharedList::add);
        System.out.println("Shared list size: " + sharedList.size());
        
        
        
        // Demonstrating overhead of creating threads
        System.out.println("\nDemonstrating overhead of creating threads:");
        List<Integer> smallNumbers = Arrays.asList(1, 2, 3);
        long start = System.currentTimeMillis();
        
        
        smallNumbers.parallelStream()
                    .map(n -> n * 2)
                    .forEach(System.out::print);
        long end = System.currentTimeMillis();
        System.out.println("\nTime taken to process small data set using parallel stream: " + (end - start) + "ms");
        
        start = System.currentTimeMillis();
        smallNumbers.stream()
                    .map(n -> n * 2)
                    .forEach(System.out::print);
        end = System.currentTimeMillis();
        System.out.println("\nTime taken to process small data set using sequential stream: " + (end - start) + "ms");
        
    }
}
