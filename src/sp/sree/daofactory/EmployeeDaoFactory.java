package sp.sree.daofactory;

import sp.sree.persistence.EmployeeDaoImpl;
import sp.sree.persistence.IEmployeeDao;

public class EmployeeDaoFactory
{
	private EmployeeDaoFactory()
	{
		
	}
	
	static private IEmployeeDao employyeeDao = null;
	
	public static IEmployeeDao getEmployeeDao()
	{
		if(employyeeDao == null) 
			employyeeDao = new EmployeeDaoImpl();
		
		return employyeeDao;
	}
}
