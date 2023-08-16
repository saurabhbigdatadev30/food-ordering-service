package com.learn.multithreading;


/*
 
 Deadlock because of circular dependency 
 
 accessResource1() & accessResource2() are running in parallel Thread . 
 
 (1) During the start of execution, accessResource1() obtains lock on obj1 & accessResource2()  obtains lock on obj2
 (2) Latter, accessResource1() tries to obtain lock on obj2 while accessResource2() tries to obtain lock on obj1
 (3) Results in deadlock since  
         [a] accessResource1() tries to obtain lock on obj2, which is currently with accessResource2() 
         [b] accessResource2() tries to obtain lock on obj1, which is currently with accessResource1() 
  
  
 
 */

public class UnderstandingDeadlock 
{
	    public static void main(String [] args) 
	    {
	    	Intersection sharedObject = new Intersection();
	    	
	        
	        Runnable r1 = (() ->		
	        	 {
	        		 while (true) {
	 	                try {
	 	                    Thread.sleep(1000);
	 	                    sharedObject.accessResource1();
	 	                } catch (InterruptedException e) {
	 	                }

	 	            }
	            });
	        
	        
	 Runnable r2 = (() ->		
       	 {
       		 while (true) 
       		 {
	                try {
	                    Thread.sleep(1000);
	                    sharedObject.accessResource2();
	                    
	                } 
	                
	                catch (InterruptedException e)  
	                {
	                }

	            }
           });
	        
	        
	        Thread thread1 = new Thread(r1);
	        Thread thread2 = new Thread(r2);
	        
	         thread1.start();
	         thread2.start();
	         
	     
	               	     
	    
	    }  
	    
	    public static class Intersection 
	    {
	        private Object obj1 = new Object();
	        private Object obj2 = new Object();

	        public void accessResource1() 
	        {
	        	System.out.println("Inside T1");
	            synchronized (obj1) {
	                System.out.println("Road A is locked by thread " + Thread.currentThread().getName());
	                System.out.println("Entering the lock of obj2 ");

	                synchronized (obj2) {
	                    System.out.println("Train is passing through road A");
	                    try {
	                        Thread.sleep(1);
	                    } catch (InterruptedException e) {
	                    }
	                }
	            }
	        }

	        
	        
	        public void accessResource2() 
	        {
	        	System.out.println("Inside T2");
	        	
	            synchronized (obj2) {
	                System.out.println("Road B is locked by thread " + Thread.currentThread().getName());
	                System.out.println("Entering the lock of obj1 ");
	                synchronized (obj1) {
	                    System.out.println("Train is passing through road B");

	                    try {
	                        Thread.sleep(1);
	                    } catch (InterruptedException e) {
	                    }
	                }
	            }
	        }
	    }
	}



/*
OUTPUT [1] 

Inside T2
Inside T1
Road A is locked by thread Thread-0
Entering the lock of obj2 
Train is passing through road A
Road B is locked by thread Thread-1
Entering the lock of obj1 

*/
