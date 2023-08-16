package com.learn.multithreading;
import java.util.Random;


public class MetricsDemo 
{
	public static void main(String[] args) 
	{
        Metrics metrics = new Metrics();

        // Create Thread[1] 
        BusinessLogic businessLogicThread1 = new BusinessLogic(metrics);
        
        // Create Thread[2] 
        BusinessLogic businessLogicThread2 = new BusinessLogic(metrics);
        
        // Create Thread[3] 
        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        
        
        // Starting the above 3 created threads 
        businessLogicThread1.setName("THREAD[A]");
        businessLogicThread1.start();
        businessLogicThread2.setName("THREAD[B]");
        businessLogicThread2.start();
        metricsPrinter.start();
    }

	
	
	// MetricsPrinter , creates a thread :-  Responsible to calculate the currentAverage ... invoking the metrics.getAverage() every 1 sec 
    public static class MetricsPrinter extends Thread 
    {
        private Metrics metrics;

        public MetricsPrinter(Metrics metrics) 
        {
            this.metrics = metrics;
        }

       // MetricsPrinter creates a Thread which invokes the metrics.getAverage() every 9 sec 
        @Override
        public void run() 
        {
            while (true) 
            {
                try {
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                }

                double currentAverage = metrics.getAverage();

                System.out.println("Current Average is " + currentAverage);
            }
        }
    }

    public static class BusinessLogic extends Thread 
    {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) 
        {
            this.metrics = metrics;
        }

        
        // This is executed by 2 threads businessLogicThread1 & businessLogicThread2
        @Override
        public void run() 
        {
            while (true) 
            {
            	System.out.println("THREAD IN EXECUTION IS :- " + Thread.currentThread().getName());
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(1000);
                    } 
                  catch (InterruptedException e) 
                {
                }

                long end = System.currentTimeMillis();

                metrics.addSample(end - start);
            }
        }
    }

    public static class Metrics 
    {
    	
    	// how many samples we captures so far ... 
        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) 
        {
        	try {
				Thread.sleep(2000);
				System.out.println("THREAD IN EXECUTION IN addSample() IS :- " + Thread.currentThread().getName());
	            double currentSum = average * count;
	            count++;
	            average = (currentSum + sample) / count;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }

        
        public double getAverage() 
        {
            return average;
        }
        
        
        /*
         

 Evaluate the Output :- 
         
THREAD IN EXECUTION IS :- THREAD[A]
THREAD IN EXECUTION IS :- THREAD[B]



THREAD IN EXECUTION IN addSample() IS :- THREAD[A]
THREAD IN EXECUTION IS :- THREAD[A]

THREAD IN EXECUTION IN addSample() IS :- THREAD[B]
THREAD IN EXECUTION IS :- THREAD[B]

THREAD IN EXECUTION IN addSample() IS :- THREAD[A]
THREAD IN EXECUTION IS :- THREAD[A]

THREAD IN EXECUTION IN addSample() IS :- THREAD[B]
THREAD IN EXECUTION IS :- THREAD[B]

Current Average is 1.25  // Thread[C] 



THREAD IN EXECUTION IN addSample() IS :- THREAD[A]
THREAD IN EXECUTION IS :- THREAD[A]


THREAD IN EXECUTION IN addSample() IS :- THREAD[B]
THREAD IN EXECUTION IS :- THREAD[B]


THREAD IN EXECUTION IN addSample() IS :- THREAD[A]
THREAD IN EXECUTION IS :- THREAD[A]

THREAD IN EXECUTION IN addSample() IS :- THREAD[A]
THREAD IN EXECUTION IS :- THREAD[A]

Current Average is 1.125
         
         
         
         
         
         
         
         
         
         
         */
    }
}
