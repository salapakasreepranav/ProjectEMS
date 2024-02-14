package sp.sree.servicefactory;

import sp.sree.service.EmployeeServiceImpl;
import sp.sree.service.IEmployeeService;

//Abstraction
public class EmployeeServiceFactory
{
	//made constructor private to avoid object creation
	private EmployeeServiceFactory() 
	{
		
	}
	
	private static IEmployeeService employeeService = null;
	
	public static IEmployeeService getEmployeeService()
	{
		//singleton design pattern
		if(employeeService == null)
		{
			employeeService =  new EmployeeServiceImpl();
		}
		
		return employeeService;
	}
}
