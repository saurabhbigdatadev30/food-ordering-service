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

public class NeedOfCompletableFutureDemo 
{

	
	public static void main(String[] args) 
	{
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);	
		
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
		
		try 
		{
			// Submit the 3 threads to the ExecutorService & returns Future object immediatly . So the main threads starts 3 threads 
			futures = executorService.invokeAll(callables); 
			
			 /*
			   Main thread continues its execution . However if we invoke future.get() before the tasks (parallel) is complete, the main thread gets blocked until
			   tasks execution are complete . Only then main () thread resumes 
			   In case of muliple  callables even if one of the task is running while the other tasks are complete, still the main thread remains blocked 
			  */
			
			for(Future<String> future : futures)
			{
				System.out.println(future.isDone());
				// placeholder to actual value
			    System.out.println("future.get = " + future.get());	
			}
		}
	       catch (InterruptedException | ExecutionException e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		executorService.shutdown();
		
		
		
	}
		
		
	}
	
	
	
	
	

