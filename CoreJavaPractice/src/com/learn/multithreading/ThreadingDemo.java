package com.learn.multithreading;

/*
  
  Define a class SharedClass.java  with increment() , decrement() methods which will execute in parallel as threads . 
  increment() method is responsible to increment the count variable while decrement() method is to decrement the count variable
  
 Both these methods will access the counter variable simuntanouslySo there will be  a race condition 
  
  

 increment() operation increments the count variable , which internally performs the following operation 
  (1) Reads the current value of count variable 
  (2) Increments the value 
  (3) Assigns the new increment value to count 
  
    
decrement() operation decrements the count variable , which internally performs the following operation 
  (1) Reads the current value of count
  (2) Decrements the value
  (3) Assigns the new decrement value to count 
  
  
  
 ########  PARALLEL EXECUTION OF  (TimeSlicing) OF THREADS  ########  ########  ########
   When increment() & decrement () methods are running simuntaneously,  Suppose the Execution Order is  :- 
 
              [a] Thread 1 on increment() method moves into running state & performs Step[1] & Step[2]  ..  Thread [1] moves back from running state to runnable state.
              
              [b] Thread 2 on decrement() method  moves into running state from runnable state . Executes Step [1] , Step [2] & Step [3]. So Thread 2 completes the operation & 
                   updates the value of count to -1. 
              
              [c] Thread 1 on increment() method resumes and performs Step[3] 
   
              
              
    ## Deep Dive ########
    So in Thread 1,  before the count variable's value could have get updated from value = 0 to 1  in Step[3] , Thread 1 stopped & Thread 2 started execution
   
    Thread 2 performs Step [1] -  So Thread 2 reads the count value as  = 0 since (Thread 1 has not yet committed  value of count variable to 1) 
    Thread 2 performs Step [2] = - 1 
    Thread 2 performs Step [3]  count = -1 
    
   So at the end of  decrement () execution value of count is set to = -1 
   
   
   When Thread 1 resumes now & executes from where it left i.e Step[3] , it reads the count value from cache  as 1 
    So in Step[3] = count is set to = 1 (Hence Thread[1] overrides the count value set as -1 by thread 2) 
  
 */


public class ThreadingDemo 
{
	    public static void main(String [] args) 
	    {
	        SharedClass sharedObject = new SharedClass();
	 
	        
	        Runnable r1 = (() ->		
	        	 {
	        		 for (int i = 0; i < 10000; i++) 
	        		 {
	                  sharedObject.increment();
	            }
	            });
	        
	        
	        Runnable r2 = (() ->		
       	    {
       	    	for (int i = 0; i < 10000; i++) 
                { 
                   sharedObject.decrement();
           }
           });
	        
	        
	        Thread thread1 = new Thread(r1);
	        Thread thread2 = new Thread(r2);
	        
	         thread1.start();
	         thread2.start();
	         
	         try {
				thread1.join();
				thread2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	         
	         // Join ensures that the main() thread only completes when T[1] & T[2] completes its execution 
	         System.out.println("get counter" + sharedObject.getCounter());
	        	     
	    
	    }  
	    
	    static class SharedClass 
	    {
	        private int counter = 0;
	 
	        public synchronized void increment() 
	        {
	            this.counter++;
	        }
	 
	        public synchronized void decrement() 
	        {
	            this.counter--;
	        }
	        
	        
	        public int getCounter() {
	            return counter;
	        }
	    }
	}


