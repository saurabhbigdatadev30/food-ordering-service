package com.learn.multithreading;



public class ThreadingDemo2 
{
	    public static void main(String [] args) 
	    {
	        SharedClass sharedObject = new SharedClass();
	        SharedClass sharedObject2 = new SharedClass();
	        
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
                   sharedObject2.decrement();
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
	         System.out.println("get counter2" + sharedObject2.getCounter());
	        	     
	    
	    }  
	    
	    static class SharedClass 
	    {
	        private int counter = 0;
	 
	        public synchronized void increment() {
	            this.counter++;
	        }
	 
	        public synchronized void decrement() {
	            this.counter--;
	        }
	        
	        
	        public int getCounter() {
	            return counter;
	        }
	    }
	}


