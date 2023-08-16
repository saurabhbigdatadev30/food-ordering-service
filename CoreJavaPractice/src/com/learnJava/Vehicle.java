package com.learnJava;

interface Car
{
    public default void drive()
    {
        System.out.println("Car is driving");
    }
}
interface Jeep
{
    public default void drive()
    {
        System.out.println("Jeep is driving");
    }
}


public class Vehicle implements Jeep,Car
{
   // @Override
    public void drive()
    {
        System.out.println("Vehicle is driving");
    }
    public static void main(String args[])
    {
        Vehicle v = new Vehicle();
        v.drive();
    }
}
