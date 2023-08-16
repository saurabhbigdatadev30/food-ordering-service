package com.learnJava.lambda;

public class RunnableLambdaExample {

    public static void main(String[] args)
    {

        /**
         * Prior Java 8 Legacy way 
         */

        Runnable runnable1 = new Runnable() 
        {
            @Override
             public void run() 
              {
               for(int i = 0 ; i <10 ;++i) {
            	   System.out.println("runnable1 = " + i);
               }
              }
        };
        
        Runnable runnable2 = new Runnable() 
        {
            @Override
             public void run() 
              {
               for(int i =0 ; i <10 ;++i) {
            	   System.out.println("runnable2 = " + i);
               }
              }
        };  
        
     Thread t1 =  new Thread(runnable1);
     Thread t2 = new Thread(runnable2);
    		 t1.start();
    		 t2.start();
      
    		 try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) 
    		 {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
      System.out.println("Finished with Threading ***********" +Thread.currentThread().getName());

      
  /*    
      //Java 8 - Lambda Syntax
       // Lambda is used to implement Functional Interfaces. A functional Interface has exactly one abstract method 

        // ()  ->  {}   
        //assigning a lambda to a variable.
        Runnable runnableLambdaMultiStatements = () -> {
        	                                              // Directly define the implementation of the run() method 
                                                           System.out.println("Lambda Way  Runnable A");
                                                           System.out.println("Lambda Way Runnable B");
                                                        };

     
       // passing lambda .. 
       new Thread(runnableLambdaMultiStatements).start();

   */    

  
     System.out.println("FUNCTIONAL PROGRAMMING Threading ***********");
      
      Runnable runnableLambdaMultiStatements1 = () -> 
      {
    	  for(int i = 0 ; i <10 ;++i) {
       	   System.out.println("runnable1 = " + i);
          }                              
      };
      
      
      Runnable runnableLambdaMultiStatements2 = () -> {
    	  for(int i = 0 ; i <10 ;++i) {
       	   System.out.println("runnable2 = " + i);
          }                              
      };

      
      Thread t3 =  new Thread(runnableLambdaMultiStatements1);
      Thread t4 = new Thread(runnableLambdaMultiStatements2);
     		 t3.start();
     		 t4.start();
       
     		 try {
 				t3.join();
 				t4.join();
 			} catch (InterruptedException e) 
     		 {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
       
       System.out.println("Finished with Threading ***********" +Thread.currentThread().getName());

    }
}
