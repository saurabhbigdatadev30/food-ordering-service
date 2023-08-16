package com.learnJava.lambda;



// STEP 1 - Define Functional Interface 

	@FunctionalInterface
	interface Calculator
	{    
	   public Integer compute(Integer a, Integer b);
	}

	
	public class FirstClassCitizenDemo2 
	{    
	   public static void main(String[] args) 
	   {   
		   
		   // STEP  2:- Define 2 Lambda functions (calcFunction1 & calcFunction2)  to implement @FunctionalInterface
		   
	      // Lambda Implementation [1] -> Assign a function to a variable
	      Calculator calcFunction1 = (a,b) -> {
	    	                                    return a * b;
	      										};

	    // Lambda Implementation [2] -> Assign a function to a variable
	      Calculator calcFunction2 = (a,b) -> {
	    	                                      return a + b;
	      										};
	      
	      										
	     // Lambda Implementation [3] -> Assign a function to a variable
	      	Calculator calcFunction3 = (a,b) -> {
	      								    	   return a / b;
	      								      	};  										
	      
	      //STEP 3:-  Passing  the Lambda Implementations  function as a argument  to another function 
	      printResult(calcFunction1, 10, 10);
	      
	      printResult(calcFunction2, 10, 10);
	      
	      printResult(calcFunction3, 10, 10);

	      
	   }

	   //Passing Lambda as argument to another Function (printResult)  as a parameter
	   static void printResult(Calculator calculator, Integer a, Integer b)
	   {
		   
	      System.out.println(calculator.compute(a, b));
	   }

	  
	}

