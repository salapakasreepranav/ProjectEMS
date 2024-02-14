package sp.sree.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sp.sree.dto.Employee;
import sp.sree.util.JdbcUtil;

//persistence layer
public class EmployeeDaoImpl implements IEmployeeDao
{
	
	
	Connection connection = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	@Override
	public String addEmployee(Employee employee)
	{
		//converting util.Date to sql.Date
		long time = employee.getDob().getTime();
		java.sql.Date sqlDob = new java.sql.Date(time);
		
		String sqlInsertQuery = "insert into employee(`name`, `dob`, `gender`, `location`, `salary`) values(?,?,?,?,?)";
				
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null)
				pstm = connection.prepareStatement(sqlInsertQuery, Statement.RETURN_GENERATED_KEYS);
			
			if(pstm != null)
			{
				pstm.setString(1, employee.getEname());
				pstm.setDate(2, sqlDob);
				pstm.setString(3, (employee.getGender().toUpperCase().charAt(0) + employee.getGender().toLowerCase().substring(1))); //Male r Female
				pstm.setString(4, employee.getLocation());
				pstm.setInt(5, employee.getSalary());
				
				
				
				int rowsAffected = pstm.executeUpdate();
				
				if(rowsAffected == 1)
				{
					rs = pstm.getGeneratedKeys();
					if(rs.next())
						return Integer.toString(rs.getInt(1));
				}
			}
			
		} 
		catch (IOException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "failed at DAO";
	}

	@Override
	public Employee searchEmployee(Integer eid) {
		
		String sqlSelectQuery = "select eid, name, dob, gender, location, salary from employee where eid=?";
		try 
		{
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null)
				pstm = connection.prepareStatement(sqlSelectQuery);
			
			if(pstm!=null)
				pstm.setInt(1, eid);
			
			if(pstm!=null)
			{
				rs = pstm.executeQuery();
			}
			
			if(rs.next())
			{
				Employee emp = new Employee(rs.getInt("eid"), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				return emp;
			}
		} 
		catch (IOException | SQLException e) 
		{
					e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String updateEmployee(Employee employee) 
	{
		String updateQuery = "update  employee  set name=?, dob=?, gender=?, location=?, salary=? where eid=?";
		
		try 
		{
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null) 
				 pstm = connection.prepareStatement(updateQuery);
			
			if(pstm!=null)
			{
				pstm.setString(1, employee.getEname());
				pstm.setDate(2, new java.sql.Date(employee.getDob().getTime()));
				pstm.setString(3, (employee.getGender().toUpperCase().charAt(0) + employee.getGender().toLowerCase().substring(1))); //Male r Female
				pstm.setString(4, employee.getLocation());
				pstm.setInt(5, employee.getSalary());
				pstm.setInt(6, employee.getEid());
			}
			
			if(pstm!=null)
			{
				int rowsAffected = pstm.executeUpdate();
				if(rowsAffected == 1)
					return "success";
			}
		} 
		catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "failed at dao";
	}

	@Override
	public String deleteEmployee(Integer eid) {
		
		String sqlDeleteQuery = "delete from employee where eid=?";
		try 
		{
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null)
				pstm = connection.prepareStatement(sqlDeleteQuery);
			
			if(pstm!=null)
				pstm.setInt(1, eid);
			
			if(pstm!=null)
			{
				int rowsAffected = pstm.executeUpdate();
				if(rowsAffected == 1)
					return "success";
				else
					return "No Employee Found";
			}
		} 
		catch (IOException | SQLException e) 
		{
					e.printStackTrace();
		}
		
		return "failed at dao";
	}

	@Override
	public List<Employee> showAllEmployees() {
		
		String sqlSelectAllQuery = "select eid, name, dob, gender, location, salary from employee";
		try 
		{
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null)
				pstm = connection.prepareStatement(sqlSelectAllQuery);
			
			if(pstm!=null)
				rs = pstm.executeQuery();
			
			if(rs != null)
			{
				List<Employee> emps = new ArrayList<Employee>();
				int i = 0;
				while(rs.next())
				{
					emps.add(new Employee(rs.getInt("eid"), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
				}
				return emps;
			}
		} 
		catch (IOException | SQLException e) 
		{
					e.printStackTrace();
		}
		
		return null;
	}

}
