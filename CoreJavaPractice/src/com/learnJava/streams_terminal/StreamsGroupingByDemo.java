package com.learnJava.streams_terminal;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

public class StreamsGroupingByDemo 

{
	
	
	 public static void main(String[] args) 
	 {
		 
		 // Get List of employees 
		  Supplier<List<Employee>> getEmplist = () ->
		  {
		    List<Employee> empList = new ArrayList<Employee>();
		    empList.add(new Employee(101, "siva", 101, "active", 2000));
		    empList.add(new Employee(102, "ready", 101, "active", 5000));
		    empList.add(new Employee(103, "raju", 102, "inactive", 6000));
		    empList.add(new Employee(104, "sunder", 102, "inaactive", 4000));
		    empList.add(new Employee(105, "sunil", 103, "active", 3500));
		    empList.add(new Employee(106, "sunath", 103, "inactive", 4200));
		    empList.add(new Employee(107, "suresh", 104, "active", 2050));
		    empList.add(new Employee(108, "sureshXX", 104, "active", 2080));
		    return empList;
		  };
		 
		  //System.out.println(getEmplist.get());
		  
		  // groupby DeptID
		  
		  Map<Integer, List<Employee>> groupByDeptID= getEmplist.get().stream().collect(Collectors.groupingBy(Employee::getEmpDepId));
		  //System.out.println(groupByDeptID);
		  
		  
		  
		  // Get max salary Department wise
		  Map<Integer, java.util.Optional<Employee>> mapMaxSalByDept =    getEmplist.get()
				  														.stream()
				  														.collect(Collectors.groupingBy(Employee::getEmpDepId , 
				  																  Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getEmpSalary)))));
		  
		  System.out.println(mapMaxSalByDept);
		  
		  


		       
		  // CASE 2 - FInd N= 3rd max salary  
		  
		  
		//nth max salary n=3
		    Stream<Employee> mapMaxSalByDept1Nth =  getEmplist.get()
		    		 								.stream()
		    		 								.sorted(Comparator.comparing(Employee:: getEmpSalary).reversed()).limit(3).skip(2);
		    
		       mapMaxSalByDept1Nth.forEach(action-> 
		       {
		    	   System.out.println("Empl Details : "+ action);
		         });
		  
		  
		  
		  
/*
		   
Employee [empId=101, empName=siva, empDepId=101, status=active, empSalary=2000], 
Employee [empId=102, empName=ready, empDepId=101, status=active, empSalary=5000], 
Employee [empId=103, empName=raju,  empDepId=102, status=inactive, empSalary=6000], 
Employee [empId=104, empName=sunder, empDepId=102, status=inaactive, empSalary=4000], 
Employee [empId=105, empName=sunil, empDepId=103, status=active, empSalary=3500], 
Employee [empId=106, empName=sunath, empDepId=103, status=inactive, empSalary=4200], 
Employee [empId=107, empName=suresh, empDepId=104, status=active, empSalary=2050]],
Employee [empId=108, empName=sureshXX, empDepId=104, status=active, empSalary=2090]]


// Group By [empDepId]
{
101=[Employee [empId=101, empName=siva, empDepId=101, status=active, empSalary=2000], 
     Employee [empId=102, empName=ready, empDepId=101, status=active, empSalary=5000]], 
	 
102=[Employee [empId=103, empName=raju, empDepId=102, status=inactive, empSalary=6000], 
     Employee [empId=104, empName=sunder, empDepId=102, status=inaactive, empSalary=4000]], 

103=[Employee [empId=105, empName=sunil, empDepId=103, status=active, empSalary=3500], 
	 Employee [empId=106, empName=sunath, empDepId=103, status=inactive, empSalary=4200]], 
	 
104=[Employee [empId=107, empName=suresh, empDepId=104, status=active, empSalary=2050], 
     Employee [empId=108, empName=sureshXX, empDepId=104, status=active, empSalary=2080]]
 
	 }
		   
		  
		   
		   */
		  
	
		  
		  
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static class Employee {

	    private int empId;
	    private String empName;
	    private int empDepId;
	    private String status="active";
	    private int empSalary;
	    
	    public Employee(int empId, String empName, int empDepId, String status, int empSalary) {
	        super();
	        this.empId = empId;
	        this.empName = empName;
	        this.empDepId = empDepId;
	        this.status = status;
	        this.empSalary = empSalary;
	    }

	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + empDepId;
	        result = prime * result + empId;
	        result = prime * result + ((empName == null) ? 0 : empName.hashCode());
	        result = prime * result + empSalary;
	        result = prime * result + ((status == null) ? 0 : status.hashCode());
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Employee other = (Employee) obj;
	        if (empDepId != other.empDepId)
	            return false;
	        if (empId != other.empId)
	            return false;
	        if (empName == null) {
	            if (other.empName != null)
	                return false;
	        } else if (!empName.equals(other.empName))
	            return false;
	        if (empSalary != other.empSalary)
	            return false;
	        if (status == null) {
	            if (other.status != null)
	                return false;
	        } else if (!status.equals(other.status))
	            return false;
	        return true;
	    }

	    public int getEmpId() {
	        return empId;
	    }

	    public void setEmpId(int empId) {
	        this.empId = empId;
	    }

	    public String getEmpName() {
	        return empName;
	    }

	    public void setEmpName(String empName) {
	        this.empName = empName;
	    }

	    public int getEmpDepId() {
	        return empDepId;
	    }

	    public void setEmpDepId(int empDepId) {
	        this.empDepId = empDepId;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public int getEmpSalary() {
	        return empSalary;
	    }

	    public void setEmpSalary(int empSalary) {
	        this.empSalary = empSalary;
	    }

	    @Override
	    public String toString() {
	        return "Employee [empId=" + empId + ", empName=" + empName + ", empDepId=" + empDepId + ", status=" + status
	                + ", empSalary=" + empSalary + "]";
	    }

}
}
