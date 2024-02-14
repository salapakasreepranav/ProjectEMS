package sp.sree.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sp.sree.daofactory.EmployeeDaoFactory;
import sp.sree.dto.Employee;
import sp.sree.persistence.IEmployeeDao;

//service layer
public class EmployeeServiceImpl implements IEmployeeService 
{

	IEmployeeDao employeeDao = null;

	@Override
	public String addEmployee(Employee employee) 
	{
		if(employeeDao == null)
			employeeDao = EmployeeDaoFactory.getEmployeeDao();
		
		if(employeeDao != null)
		{
			String statusMessage = employeeDao.addEmployee(employee);
			return statusMessage;
		}
		else
			return "failed at service";
	}

	@Override
	public Employee searchEmployee(Integer eid)
	{
		if(employeeDao == null)
			employeeDao = EmployeeDaoFactory.getEmployeeDao();
		
		if(employeeDao != null)
		{
			Employee employee = employeeDao.searchEmployee(eid);
			return employee;
		}
		
		return null;
	}

	@Override
	public String updateEmployee(Employee employee) 
	{
		if(employeeDao == null)
			employeeDao = EmployeeDaoFactory.getEmployeeDao();
		
		if(employeeDao != null) {
			String status = employeeDao.updateEmployee(employee);
			return status;
		}
		
		return "failed at service";
	}
	

	@Override
	public String deleteEmployee(Integer eid) {
		
		if(employeeDao == null)
			employeeDao = EmployeeDaoFactory.getEmployeeDao();
		
		if(employeeDao != null) {
			String status = employeeDao.deleteEmployee(eid);
			return status;
		}
		
		return "failed at service";
	}

	@Override
	public List<Employee> showAllEmployees() 
	{
		if(employeeDao == null)
			employeeDao = EmployeeDaoFactory.getEmployeeDao();
		
		if(employeeDao != null)
		{
			List<Employee> employee = employeeDao.showAllEmployees();
			return employee;
		}
		
		return null;
	}


}
