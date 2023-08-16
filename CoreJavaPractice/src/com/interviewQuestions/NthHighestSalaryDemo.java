package com.interviewQuestions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/*
 https://www.youtube.com/watch?v=eBDN04LlEOg
https://github.com/Java-Techie-jt/java8/blob/master/NthHighestSalaryDemo.java


 To Do : Get Nth salary of Employee .  We have a Employee Map with (name , salary)
 
  (nameX1 , salary 1)
  (nameX2 , salary 2)
  (nameX3 , salary 3)
  (nameX4 , salary 2)
  (nameX5 , salary 1)
  (nameX6 , salary 2)
  (nameX7 , salary 3)
 
 
 Step 1 - Apply GroupBy [Salary] on empMap (name , salary)
 
 empMapgroupingBySalary = 
 
     (salary 1 , [nameX1, nameX5, ... ])
     (salary 2 , [nameX2, nameX4 , nameX6 , ... ])
     (salary 3 , [nameX3, nameX7, ... ])
    
    
    Map<Integer, List<String> > empMapgroupingBySalary = map.entrySet().
												                  stream().
				                                                  collect(Collectors.groupingBy(Map.Entry::getValue, 
				                                            	  Collectors.mapping(Map.Entry::getKey , Collectors.toList())));
		
    
    
    
    
 
 
 Step 2  - Sort the Map returned in Step -1 i.e empMapgroupingBySalary , on key in desc order
 
 
 List<Entry<Integer, List<String>>> collect = empMapgroupingBySalary.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());
 
 
 
 */






public class NthHighestSalaryDemo {

	public static void main(String[] args) {

		Map<String, Integer> map1 = populateEmployees();
		
		//sortempMapBySalary( map1);
		
		System.out.println(getDynamicNthHighestSalary(2, map1));

	}

	
	/*
	  BEFORE SORTING
			{daniel=1300, ankit=1200, tom=1300, james=1200, micael=1000, bhavna=1200, anil=1000}
			
		SORTED MAP
			[daniel=1300, tom=1300, ankit=1200, james=1200, bhavna=1200, micael=1000, anil=1000]
	  
	 */
	
	public static List<Entry<String, Integer>> sortempMapBySalary(Map<String, Integer> empMap) 
	{
		System.out.println("BEFORE SORTING");
		System.out.println(empMap);
		List<Entry<String, Integer>> sortedEmployeeMap =  empMap.
														  entrySet().
														  stream().
														  sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());
		System.out.println("SORTED MAP");
		System.out.println(sortedEmployeeMap);
		return sortedEmployeeMap;
		
	}
	
	
	

	public static Map.Entry<Integer, List<String>> getDynamicNthHighestSalary(int num, Map<String, Integer> map) 
	{
		
		// Step 1 - Group By Salary . Get the empNames -> groupBySalary()
		Map<Integer, List<String> > empMapgroupingBySalary = map.entrySet().
												                  stream().
				                                                  collect(Collectors.groupingBy(Map.Entry::getValue, 
				                                            	  Collectors.mapping(Map.Entry::getKey , Collectors.toList())));
		
		/*
		  Original Map 
		  {daniel=1300, ankit=1200, tom=1300, james=1200, micael=1000, bhavna=1200, anil=1000}
		 
		  GroupBy  (Salary) empMapgroupingBySalary = 
		  
		  {
		      1200= [ankit, james, bhavna], 
		      1300= [daniel, tom], 
		      1000= [micael, anil]
		   }
		  
		 */
		
		
		System.out.println(empMapgroupingBySalary);
		
		// Step 2 - Sort the GroupedBy Salary map [empMapgroupingBySalary]
		List<Entry<Integer, List<String>>> sortempMapGroupBySalary1 =  sortempMapGroupBySalary(empMapgroupingBySalary);
		System.out.println("Sorted Group By Map");
		System.out.println(sortempMapGroupBySalary1);
		
		/*
		  sortempMapGroupBySalary1 = 
		         [1300=[daniel, tom], 
		         1200=[ankit, james, bhavna], 
		         1000=[micael, anil]]
		 */
		
		
		return sortempMapGroupBySalary1.get(num - 1);
	}
	
	
	

	private static List<Entry<Integer, List<String>>>  sortempMapGroupBySalary(Map<Integer, List<String>> empMapgroupingBySalary) 
	{
		List<Entry<Integer, List<String>>> collect = empMapgroupingBySalary.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());

			return collect;
		
			}

	private static Map<String, Integer> populateEmployees()
	{
		Map<String, Integer> map1 = new HashMap<>();
		map1.put("anil", 1000);
		map1.put("bhavna", 1300);
		map1.put("micael", 1500);
		map1.put("tom", 1600);// output
		map1.put("ankit", 1200);
		map1.put("daniel", 1700);
		map1.put("james", 1400);

		//Map.Entry<String, Integer> results = getNthHighestSalary(4, map1);
		//System.out.println(results);

		Map<String, Integer> map2 = new HashMap<>();
		map2.put("anil", 1000);
		map2.put("ankit", 1200);
		map2.put("bhavna", 1200);
		map2.put("james", 1200);
		map2.put("micael", 1000);
		map2.put("tom", 1300);
		map2.put("daniel", 1300);
		return map2;
	}
}