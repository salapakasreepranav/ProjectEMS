package sp.sree.service;

import java.util.Date;
import java.util.List;

import sp.sree.dto.Employee;

public interface IEmployeeService
{
	//operations to be implemented
	public String addEmployee(Employee employee);
	public Employee searchEmployee(Integer eid);
	public String updateEmployee(Employee employee);
	public List<Employee> showAllEmployees();
	public String deleteEmployee(Integer eid);
}