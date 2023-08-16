package com.learn.multithreading;


public class RunnableCriticalSection 

{
	
    public static void main(String[] args) throws InterruptedException 
     {
    	
    	// Shared between Threads
        InventoryCounter inventoryCounter =  new InventoryCounter();
        //InventoryCounter inventoryCounter2 = new InventoryCounter();
        
        // InventoryCounter is shared between 2 threads [IncrementingThread & DecrementingThread]
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);
        

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
    }

    public static class DecrementingThread extends Thread 
    {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) 
        {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() 
        {
            for (int i = 0; i < 10000; i++) 
            {
                inventoryCounter.decrement();
            }
        }
    }

    public static class IncrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() 
        {
            for (int i = 0; i < 10000; i++) 
            {
                inventoryCounter.increment();
            }
        }
    }

    private static class InventoryCounter 
    
    {
    	
    	Object lock  = new Object();
        private int items = 0;

        public  void increment() 
        {
        	synchronized(this.lock) {
            items++;
        	}
        }

        public  void decrement() 
        {
        	synchronized(this.lock) {
            items--;
        	}
        }

        public int getItems() {
            return items;
        }
    }
}
