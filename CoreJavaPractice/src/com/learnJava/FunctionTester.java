package com.learnJava;


@FunctionalInterface
interface Calculator<X, Y> 
{    
   public X compute(X a, Y b);
}

public class FunctionTester 
{
	  public static void main(String[] args) 
	   {               
	      //Assign a function to a variable
	      Calculator<Integer, Integer> calculatorFunction = (a , b) -> 
	                                                            {
	                                                            	return a * b;
	                                                              };

	      //call a function using function variable
	      System.out.println(calculatorFunction.compute(2, 3));

	      //Pass the function as a parameter
	      printResult(calculatorFunction, 2, 3);

	      //Get the function as a return result
	      Calculator<Integer, Integer> calculator1 = getCalculator();
	      System.out.println(calculator1.compute(2, 3));
	   }

	   private static void printResult(Calculator<Integer, Integer> calculatorFunction, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	//Function as a parameter
	   static void printResult(Calculator<Integer, Integer> calculator, Integer a, Integer b)
	   {
	      System.out.println(calculator.compute(a, b));
	   }

	   //Function as return value
	   static Calculator<Integer, Integer> getCalculator()
	   {
		   return (a,b) -> a * b;
	       
	   }
	
	
}
