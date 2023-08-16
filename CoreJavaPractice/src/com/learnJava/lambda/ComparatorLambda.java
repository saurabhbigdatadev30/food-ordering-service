package com.learnJava.lambda;

import java.util.Comparator;

public class ComparatorLambda {

    public static int compareTwoIntegers(Comparator<Integer> comparator, int a, int b)
    {

        return comparator.compare(a,b);
    }

    public static void main(String[] args) {

        /**
         * Prior JAVA 8
         */
        Comparator<Integer> comparator  = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2); // 0 -> if both are equal
                                        // 1 -> if o1 > o2
                                        //-1 -> if o1<o2
            }
        };

       // System.out.println(comparator.compare(1,2));

        /**
         * In JAVA 8
         */
        Comparator<Integer> comparatorLambda = (Integer  a, Integer b) -> {
        	                                                                 return a.compareTo(b);
        																	};
       // Compares its two arguments for order. Returns a negative integer,zero, or a positive integer as the first argument is less than, equalto, or greater than the second.
        System.out.println(comparatorLambda.compare(10,2));
    }
}
