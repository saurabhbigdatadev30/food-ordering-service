package com.learnJava.functionalInterfaces;


@FunctionalInterface
public interface MyFunctionalInterface 
{
		
	int testMethod(int i , int j);

	
	default void m2() {
		System.out.println("Default method-1");
	}

	default void m3() {
		System.out.println("Default method-2");
	}

	static void m4() {
		System.out.println("static method-1");
	}

}
