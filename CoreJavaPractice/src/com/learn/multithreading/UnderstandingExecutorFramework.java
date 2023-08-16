package com.learn.multithreading;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UnderstandingExecutorFramework 
{

	
	public static void main(String[] args) 
	{
		
		
		int  corePoolSize  =    5;
		int  maxPoolSize   =   10;
		long keepAliveTime = 5000;


/*
	
###  UNDERSTANDING ThreadPoolExecutor  ### ### ### ### 		 
		 
The java.util.concurrent.ThreadPoolExecutor is an implementation of the ExecutorService interface. The ThreadPoolExecutor executes the given task (Callable or Runnable) using 
one of its internally pooled threads.

The thread pool contained inside the ThreadPoolExecutor can contain a varying amount of threads. The number of threads in the pool is determined by these variables:

corePoolSize
maximumPoolSize

(a)If less than corePoolSize threads are created in the the thread pool when a task is delegated to the thread pool, then a new thread is created, even if idle threads exist in the pool.

(b) If the internal queue of tasks is full, and corePoolSize threads or more are running, but less than maximumPoolSize threads are running, then a new thread is created to execute the task.	 
		 

## Creation		 
  Creates a new ThreadPoolExecutor with the given initial parameters, relies on defaultThreadFactory default thread factory , ThreadPoolExecutor.AbortPolicy and default rejected 
   execution handler.
  It may be more convenient to use one of the factory methods instead of this general purpose constructor.
  
  ExecutorService executorService = Executors.newFixedThreadPool(10);
  
   Parameters :- 
   
   corePoolSize = the number of threads to keep in the pool, even if they are idle, unless {@code allowCoreThreadTimeOut} is 
   maximumPoolSize = the maximum number of threads to allow in the pool .
   keepAliveTime = when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating.
   unit =  the time unit for the {@code keepAliveTime} argument
   workQueue = the queue to use for holding tasks before they are
           executed.  This queue will hold only the {@code Runnable}
           tasks submitted by the {@code execute} method
		 
		 */
		
		
// ########  Understanding the number of threads to be configured  for the tasks ######## 
	/*
	  Note :-  No of threads = No of cores in CPU 
	  
		  (1) In case of CPU intensive operations :-  The no of tasks allocated can be slightly just more than the no of the cores/threads available . Since its CPU based computation so the threads 
		      will be busy most of the time executing  the tasks . So time slicing may not add much benefit here 
		       
		  (2) In case of IO intensive operations :- The threads will be idle for the IO operations and can be swiched to perform another task . So we can have more no of tasks on available no of threads
		     So time slicing  adds  benefit here 
		  
		   
		  
		  
		 */
		
		

		//Step [1] :- Creating ThreadPool
		
		// Option[1] 
		ExecutorService threadPoolExecutor =  new ThreadPoolExecutor(
		                corePoolSize,
		                maxPoolSize,
		                keepAliveTime,
		                TimeUnit.MILLISECONDS,
		                new LinkedBlockingQueue<Runnable>()
		                );
		
		// Option[2] 
		ExecutorService executorService = Executors.newFixedThreadPool(10);	
		
		/*
		  Internals of newFixedThreadPool :- 
		  
		     public static ExecutorService newFixedThreadPool(int nThreads) 
		      {
              return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
               }
		 
		 
		 */
		
		

		
		// Step [2] :- Creating Threads  => Thread[1] ,Thread[2] , Thread[3]
		
		// Thread[1] 
		Set<Callable<String>> callables = new HashSet<Callable<String>>();

		callables.add(new Callable<String>() 
		{
		    public String call() throws Exception 
		    {
		    	Thread.sleep(6000);
		        return "Task 1";
		    }
		});
		
		// Thread[2] 
		callables.add(new Callable<String>() 
		{
		    public String call() throws Exception 
		    {
		        return "Task 2";
		    }
		});
		
		
		// Thread[3]
		callables.add(new Callable<String>()
		{
		    public String call() throws Exception 
		    {
		        return "Task 3";
		    }
		});

		
	//  Step [3] :-Create Future Object	
		List<Future<String>> futures;
		
	/*
	   (1) Future Object is returned immediately after the callable task is submitted to the pool . When the task is finished the status of future (isDone = true) & returns a value 
	   (2) The main Thread invocation [future.get()] is blocked until the task is complete .
	   (3) 
	 	
	 	
	 */
		
		
		
		try 
		{
			// Step [4] :- Submit the 3 threads to the ExecutorService
			futures = executorService.invokeAll(callables);
			
			for(Future<String> future : futures)
			{
				System.out.println(future.isDone());
				
			    System.out.println("future.get = " + future.get());	
			}
		}
	       catch (InterruptedException | ExecutionException e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		// waits for all the tasks to be complete 
		executorService.shutdown();
		
		// shutdownNow() -> Will try to stop the task running and exists , the runnables will not start executing & will be returned .
		// awaitTermination(time period) -> 
		
	}
		
		
	}
	
	
	
	
	

