package com.learn.multithreading;


/*
 
 DataRace Condition :- NEED OF VOLATILE 
 SharedClass defines two methods increment() method & checkForDataRace() method which will run in parallel .
 
 increment() method increments x & y variables while checkForDataRace() checks if y > x . THis condition will not be true ideally, if the execution ordering is not changed 
 
  Declaring a shared variable as volatile guarantees :- 
    [A] The code that comes before access to a volatile variable will be executed before the access . 
    [B] The code that comes after access to a volatile variable will be executed after the access . 
  
 */


public class DataRace 
{

	public static void main(String[] args) 
	 {
		
		
        SharedClass sharedClass = new SharedClass();
        
        Runnable r1 = (() ->		
        {
            for (int i = 0; i < Integer.MAX_VALUE; i++) 
            {
                sharedClass.increment();
            }
        });

        
        
        Runnable r2 = (() -> 
        {
            for (int i = 0; i < Integer.MAX_VALUE; i++) 
            {
                sharedClass.checkForDataRace();
            }

        });

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        
         thread1.start();
         thread2.start();
    }

	
	
    public static class SharedClass 
    {
        private  volatile int x = 0;
        private  volatile int y = 0;

        // THREAD[1] 
        public void increment() 
        {
            x++;
            y++;
        }

        
        // THREAD[2] 
        public void checkForDataRace()
        {
            if (y > x) 
            {
                System.out.println("y > x - Data Race is detected");
            }
        }
    }
}
