package com.learnJava.streams;
import java.util.ArrayList;
import java.util.List;
public class ParallelStreamDemo 
{
	
	 public static void main(String[] args) {
	        // Create a list of integers
	        List<Integer> numbers = new ArrayList<>();
	        for (int i = 0; i < 100; i++) {
	            numbers.add(i);
	        }

	        // Sequential stream example
	        numbers.stream()
	                .forEach(System.out::println);

	        // Parallel stream example
	        System.out.println("************* PARALLEL STREAMS ****************8");
	        numbers.parallelStream()
	                .forEach(System.out::println);
	    }

}
