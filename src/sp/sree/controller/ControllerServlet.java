package sp.sree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.InflaterOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sp.sree.dto.Employee;
import sp.sree.service.IEmployeeService;
import sp.sree.servicefactory.EmployeeServiceFactory;


@WebServlet("/controller/*") // * represents extension url mapping, we can give anything after '/controller/'
public class ControllerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		IEmployeeService employeeService = EmployeeServiceFactory.getEmployeeService();
		
		String pathInfo = request.getPathInfo(); // after /controller
		if(pathInfo.startsWith("/addEmployee"))
		{
			String name = request.getParameter("name");
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");
			String location = request.getParameter("location");
			Integer salary = Integer.valueOf(request.getParameter("salary"));
			
			Employee employee = new Employee(name, new SimpleDateFormat("yyyy-MM-dd").parse(dob), gender, location, salary);
			String message = employeeService.addEmployee(employee);
			
			out.print("<body bgcolor=\"lightblue\">");
			if(!message.toLowerCase().startsWith("failed"))
				out.print("<h1>Employee Added Successfully </h1><br><h1>Employee ID: " + message + "</h1>");
			else
				out.print("<h1>" + message + "</h1>");
			out.print("</body >");
			
			out.close();
		}
		
		else if(pathInfo.startsWith("/searchEmployee"))
		{
			Integer eid = Integer.valueOf(request.getParameter("eid"));
			
			Employee employee = employeeService.searchEmployee(eid);
			
			if(employee != null)
			{
				out.print("<body bgcolor=\"lightblue\">");
				out.print("<table style=\"border-collapse: collapse; margin: 25px 0; font-size: 0.9em; font-family: sans-serif; min-width: 400px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);\">\r\n" + 
						"  <caption style=\"font-size: 1.2em; font-weight: bold; color: white; background-color: #009879; padding: 10px;\">Employee Details</caption>\r\n" + 
						"  <tr style=\"background-color: #009879; color: white; text-align: left;\">\r\n" + 
						"    <th style=\"padding: 12px 15px;\">ID</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Name</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Date of Birth</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Gender</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Location</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Salary</th>\r\n" + 
						"  </tr>\r\n" + 
						"  <tr style=\"border-bottom: 1px solid #dddddd;\">\r\n" + 
						"    <td style=\"padding: 12px 15px;\">"
						+ 		employee.getEid()
						+ 	"</td>\r\n" + 
						"    <td style=\"padding: 12px 15px;\">"
						+ 		employee.getEname()
						+ 	"</td>\r\n" + 
						"    <td style=\"padding: 12px 15px;\">"
						+ 		new SimpleDateFormat("dd-MMM-yyy").format(employee.getDob())
						+ 	"</td>\r\n" + 
						"    <td style=\"padding: 12px 15px;\">"
						+ 		employee.getGender()
						+ 	"</td>\r\n" + 
						"    <td style=\"padding: 12px 15px;\">"
						+ 		employee.getLocation()
						+ 	"</td>\r\n" + 
						"    <td style=\"padding: 12px 15px;\">"
						+ 		employee.getSalary()
						+ 	"</td>\r\n" + 
						"  </tr>\r\n" + 
						"  </table>");
				out.print("</body >");
			}
			else
			{
				out.print("<body bgcolor=\"lightblue\">");
				out.print("<h1>No Employee Found with ID: " + eid + "</h1>");
				out.print("</body >");
			}
			
			out.close();
		}
		
		else if(pathInfo.startsWith("/deleteEmployee"))
		{
			Integer eid = Integer.valueOf(request.getParameter("eid"));
			
			String statusMessage = employeeService.deleteEmployee(eid);
			
			out.print("<body bgcolor=\"lightblue\">");
			if(statusMessage.startsWith("failed"))
				out.println("<h1>" + statusMessage + "</h1>");
			else if(statusMessage.equalsIgnoreCase("No Employee Found"))
				out.println("<h1>" + "No Employee Found with ID: " + eid + "</h1>");
			else
				out.println("<h1>" + "Employee Deleted Successfully" + "</h1>");
			out.print("</body >");
		}
		
		else if(pathInfo.startsWith("/listEmployees"))
		{
			List<Employee> employees = employeeService.showAllEmployees();
			
			if(!employees.isEmpty())
			{
				out.print("<body bgcolor=\"lightblue\">");
				out.print("<table style=\"border-collapse: collapse; margin: 25px 0; font-size: 0.9em; font-family: sans-serif; min-width: 400px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);\">\r\n" + 
						"  <caption style=\"font-size: 1.2em; font-weight: bold; color: white; background-color: #009879; padding: 10px;\">All Employees</caption>\r\n" + 
						"  <tr style=\"background-color: #009879; color: white; text-align: left;\">\r\n" + 
						"    <th style=\"padding: 12px 15px;\">ID</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Name</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Date of Birth</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Gender</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Location</th>\r\n" + 
						"    <th style=\"padding: 12px 15px;\">Salary</th>\r\n" + 
						"  </tr>\r\n");
				
				for(Employee employee: employees)
				{
					out.print("  <tr style=\"border-bottom: 1px solid #dddddd;\">\r\n" +
							"    <td style=\"padding: 12px 15px;\">"
							+ 		employee.getEid()
							+ 	"</td>\r\n" + 
							"    <td style=\"padding: 12px 15px;\">"
							+ 		employee.getEname()
							+ 	"</td>\r\n" + 
							"    <td style=\"padding: 12px 15px;\">"
							+ 		new SimpleDateFormat("dd-MMM-yyy").format(employee.getDob())
							+ 	"</td>\r\n" + 
							"    <td style=\"padding: 12px 15px;\">"
							+ 		employee.getGender()
							+ 	"</td>\r\n" + 
							"    <td style=\"padding: 12px 15px;\">"
							+ 		employee.getLocation()
							+ 	"</td>\r\n" + 
							"    <td style=\"padding: 12px 15px;\">"
							+ 		employee.getSalary()
							+ 	"</td>\r\n" + 
							"  </tr>\r\n");
				}
						
				out.print("  </table>");
				out.print("</body >");
			}
			else
			{
				out.print("<body bgcolor=\"lightblue\">");
				out.print("<h1>Error While Fetching Employees</h1>");
				out.print("</body >");
			}
			
			out.close();
		}
		
		else if(pathInfo.startsWith("/editEmployee"))
		{
			Integer eid = Integer.valueOf(request.getParameter("eid"));
			
			Employee employee = employeeService.searchEmployee(eid);
			
			if(employee != null)
			{
				out.print("<body bgcolor=\"lightblue\">\r\n" + 
						"		<form method=\"post\" action='./updateEmployee'>\r\n" + 
						"			<table align='center'>\r\n"
						+ "				<tr>\r\n" + 
							"				<th>Employee ID</th>\r\n" + 
							"				<td><input type='text' name='eid' readonly value='" + employee.getEid() + "'/></td>\r\n" + 
						"				</tr>" + 
						
						"				<tr>\r\n" + 
						"					<th>New Name</th>\r\n" + 
						"					<td><input type=\"text\" name='name' value='" + employee.getEname() + "' required/></td>\r\n" + 
						"				</tr>\r\n" + 
						"				<tr>\r\n" + 
						"					<th>New DOB</th>\r\n" + 
						"					<td><input type=\"date\" name='dob' value='" + new SimpleDateFormat("yyyy-MM-dd").format(employee.getDob()) + "' required/></td>\r\n" + 
						"				</tr>\r\n" + 
						"				<tr>				\r\n" + 
						"					<th>New Gender</th>\r\n"); 
										if(employee.getGender().toLowerCase().startsWith("m"))
										{
											out.print("<td><input type=\"radio\" name=\"gender\" value=\"Male\" checked required>Male\r\n" + 
													"					<input type=\"radio\" name=\"gender\" value=\"Female\" required>\r\n" + 
													"					Female</td>\r\n" + 
													"				</tr>\r\n");
										}
										else
										{
											out.print("<td><input type=\"radio\" name=\"gender\" value=\"Male\"  required>Male\r\n" + 
													"					<input type=\"radio\" name=\"gender\" checked value=\"Female\" required>\r\n" + 
													"					Female</td>\r\n" + 
													"				</tr>\r\n");
										}
											 
										out.print("<tr>\r\n" + 
						"					<th>New Location</th>\r\n" + 
						"					<td><input type=\"text\" name='location' value='" + employee.getLocation() + "' required/></td>\r\n" + 
						"				</tr>\r\n" + 
						"				<tr>\r\n" + 
						"					<th>New Salary</th>\r\n" + 
						"					<td><input type=\"number\" name='salary' value='" + employee.getSalary() + "' required/></td>\r\n" + 
						"				</tr>\r\n" + 
						"				<tr>\r\n" + 
						"					<td></td>\r\n" + 
						"					<td><input type=\"submit\" value='Update' /></td>\r\n" + 
						"				</tr>\r\n" + 
						"			</table>\r\n" + 
						"		</form>\r\n" + 
						"\r\n" + 
						"</body>");
										
																			
			}
			else
			{
				out.print("<body bgcolor=\"lightblue\">");
				out.print("<h1>No Employee Found with ID: " + eid + "</h1>");
				out.print("</body >");
			}
		}
			
			
		else if(pathInfo.startsWith("/updateEmployee"))	
		{
			Integer eid =  Integer.valueOf(request.getParameter("eid"));
			String name = request.getParameter("name");
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");
			String location = request.getParameter("location");
			Integer salary = Integer.valueOf(request.getParameter("salary"));
			
			Employee newEmmployee = new Employee(eid, name, new SimpleDateFormat("yyyy-MM-dd").parse(dob), gender, location, salary);
			
			String statusMessage = employeeService.updateEmployee(newEmmployee);
			if(statusMessage.startsWith("failed"))
			{
				out.print("<body bgcolor=\"lightblue\">");
				out.print("<h1>Error While Updating Employee Details: " + statusMessage + "</h1>");
				out.print("</body >");
			}	
			else
			{
				out.print("<body bgcolor=\"lightblue\">");
				out.print("<h1>Employee Details Updated Sunccessfully</h1>");
				out.print("</body >");
			}
			
			out.close();
		}

	}

}
