/*package sp.sree.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import sp.sree.dto.Employee;
import sp.sree.service.EmployeeServiceImpl;
import sp.sree.service.IEmployeeService;
import sp.sree.servicefactory.EmployeeServiceFactory;

//controller layer
public class TestApp 
{
	private static IEmployeeService employeeService =null;
	static private Scanner sc = null;
	
	public static void main(String[] args) throws ParseException 
	{	
		if(employeeService == null)
			employeeService = EmployeeServiceFactory.getEmployeeService(); //creating object of service layer (EmployeeServiceImpl)
		if(sc == null)
			sc = new Scanner(System.in);
		
		System.out.println("Welcome to Sree.SP");
		System.out.println("------------------");
		System.out.println("Menu: ");
		
		loop: while(true)
		{
			System.out.println("1. Add an Employee \n"
					+ "2. Show Employee Details \n"
					+ "3. Change Employee Details \n"
					+ "4. Show All Employees \n"
					+ "5. Remove an Employee \n"
					+ "6. Exit");
			int n = sc.nextInt();
			if(sc.hasNextLine())
				sc.nextLine();
			
			switch(n)
			{
				case 1:
					addEmployee();
					break;
					
				case 2:
					searchEmployee();
					break;
					
				case 3:
					updateEmployee();
					break;
					
				case 4:
					List<Employee> employees = employeeService.showAllEmployees();
					if(employees != null)
					{
						System.out.printf("%-8s%-20s%-14s%-8s%-14s%-10s\n", "EID", "NAME", "DOB", "GENDER", "LOCATION", "SALARY");
						for(Employee employee: employees)
							System.out.printf("%-8d%-20s%-14s%-8s%-14s%-10d\n", employee.getEid(), employee.getEname(), new SimpleDateFormat("dd-MMM-yyyy").format(employee.getDob()), employee.getGender(), employee.getLocation(), employee.getSalary());
					}
					else
						System.out.println("Error while Fetching the Employees");
					
					break;
					
				case 5:
					removeEmployee();
					break;
					
				case 6:
					System.out.println("Thank You");
					break loop;	
			}
		}
		
		
	}

	private static void updateEmployee() throws ParseException {
		System.out.println("Enter Employee ID");
		int eid = sc.nextInt();
		if(sc.hasNextLine())
			sc.nextLine();
		
		Employee employee = employeeService.searchEmployee(eid);
		
		if(employee != null)
		{
			System.out.println("Employee ID: " + employee.getEid());
			System.out.println("Existing Name: " + employee.getEname());
			System.out.println("Enter New Name: (Press Enter to keep same) " );
			String name = sc.nextLine();
			if(name.isEmpty())
				name = employee.getEname();
			
			System.out.println("Existing DOB: " + new SimpleDateFormat("dd-MM-yyyy").format(employee.getDob()));
			System.out.println("Enter New DOB(dd-MM-yyyy): (Press Enter to keep same) " );
			String dob = sc.nextLine();
			if(dob.isEmpty())
				dob = new SimpleDateFormat("dd-MM-yyyy").format(employee.getDob());
			
			System.out.println("Existing Gender: " + employee.getGender());
			System.out.println("Enter New Gender: (Press Enter to keep same) " );
			String gender = sc.nextLine();
			if(gender.isEmpty())
				gender = employee.getGender();
			
			System.out.println("Existing Location: " + employee.getLocation());
			System.out.println("Enter New Location: (Press Enter to keep same) " );
			String location = sc.nextLine();
			if(location.isEmpty())
				location = employee.getLocation();
			
			System.out.println("Existing Salary: " + employee.getSalary());
			System.out.println("Enter New Salary: (Press Enter to keep same) " );
			String salary = sc.nextLine();
			if(salary.isEmpty())
				salary = Integer.toString(employee.getSalary());
			
			Employee newEmployee = new Employee(eid, name, new SimpleDateFormat("dd-MM-yyyy").parse(dob), gender, location, Integer.parseInt(salary));
			
			String statusMessage = employeeService.updateEmployee(newEmployee);
			
			if(statusMessage.startsWith("failed"))
				System.out.println(statusMessage);
			else
				System.out.println("Employee Details Modified Successfully");
		}
		else
			System.out.println("No Employee Found with ID: " + eid);
	}

	private static void removeEmployee() {
		System.out.println("Enter Employee ID");
		int eid = sc.nextInt();
		if(sc.hasNextLine())
			sc.nextLine();
		
		String statusMessage = employeeService.deleteEmployee(eid);
		if(statusMessage.startsWith("failed"))
			System.out.println(statusMessage);
		else if(statusMessage.equalsIgnoreCase("No Employee Found"))
			System.out.println("No Employee Found with ID: " + eid);
		else
			System.out.println("Employee Deleted Successfully");
	}

	private static void searchEmployee()
	{
		System.out.print("Enter Employee ID: ");
		int eid = sc.nextInt();
		if(sc.hasNextLine())
			sc.nextLine();
		
		Employee employee = employeeService.searchEmployee(eid);
		if(employee != null)
		{
			System.out.printf("%-8s%-20s%-14s%-8s%-14s%-10s\n", "EID", "NAME", "DOB", "GENDER", "LOCATION", "SALARY");
			System.out.printf("%-8d%-20s%-14s%-8s%-14s%-10d\n", employee.getEid(), employee.getEname(), new SimpleDateFormat("dd-MMM-yyyy").format(employee.getDob()), employee.getGender(), employee.getLocation(), employee.getSalary());
		}
		else
			System.out.println("No Employee Found With ID: " + eid);
	}

	private static void addEmployee() throws ParseException 
	{		
		System.out.print("Employee Name: ");
		String name = sc.nextLine();
		System.out.print("\nDOB (dd-MM-yyyy) ");
		String dob = sc.nextLine();
		System.out.print("\nGender: ");
		String gender = sc.nextLine();
		System.out.print("\nLocation: ");
		String location = sc.nextLine();
		System.out.print("\nSalary \n");
		int salary = sc.nextInt();
		if(sc.hasNextLine())
			sc.nextLine();
		
		
		String status = employeeService.addEmployee(name, new SimpleDateFormat("dd-MM-yyyy").parse(dob), gender, location, salary);
		
		if(!status.startsWith("failed"))
			System.out.println("Employee Added Successfully \n"
					+ "Employee ID: " + status + "\n");
		else
			System.out.println(status + "\n");
	}
}
*/