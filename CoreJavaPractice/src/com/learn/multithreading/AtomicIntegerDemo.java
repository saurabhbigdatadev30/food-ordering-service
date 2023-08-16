package com.learn.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo 
{

    public static void main(String[] args) throws InterruptedException 
    {
    	
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> 
        {
            for (int i = 0; i < 1000000; i++) 
            {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> 
        {
            for (int i = 0; i < 1000000; i++) 
            {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Count: " + counter.getCount());
    }

    
    
    public static class Counter 
    {
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() 
        {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }
}
