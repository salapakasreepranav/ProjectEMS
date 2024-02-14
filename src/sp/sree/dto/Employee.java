package sp.sree.dto;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Integer eid;
	private String ename;
	private Date dob;
	private String gender;
	private String location;
	private Integer salary;
	
	
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	
	public Employee(Integer eid, String ename, Date dob, String gender, String location, Integer salary) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.dob = dob;
		this.gender = gender;
		this.location = location;
		this.salary = salary;
	}
	
	
	
	public Employee(String ename, Date dob, String gender, String location, Integer salary) {
		super();
		this.ename = ename;
		this.dob = dob;
		this.gender = gender;
		this.location = location;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", dob=" + dob + ", gender=" + gender + ", location="
				+ location + ", salary=" + salary + "]";
	}
}
