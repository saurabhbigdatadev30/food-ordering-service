package com.learn.multithreading;

public class RunnableLambdaDemo
{
	
	/*
	   runnableA &  runnableB are executing in time slicing mode (simuntaneous execution)
	    
	    
	 */

    public static void main(String[] args)
    {        
        Runnable runnableA = () -> {
        	   {
                   for(int i =0 ; i <10 ;++i) {
              	   System.out.println("runnable1 = " + i);
                 }
               }
        	   };
          
          
          
          Runnable runnableB = () -> {
                // Directly define the implementation of the run() method 
        	     for(int i =0 ; i <10 ;++i) {
           	     System.out.println("runnable2 = " + i);
              }
            };
        
        

     Thread t1 =  new Thread(runnableA);
     Thread t2 =  new Thread(runnableB);
    		 t1.start();
    		 t2.start();
      
    		 try {
				t1.join();
				t2.join();
			} 
    		 
    		 catch (InterruptedException e) 
    		 {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
      System.out.println("Finished with Threading ***********" +Thread.currentThread().getName());

        }
      
}
      
   
     
