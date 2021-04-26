package employees;
import union.Syndicate;
import wage.Paycheck;

/**
 * Employee
 */

public class Employee {

    private int employeeID;
    private String name;
    private String address;
    private boolean filiated;
    private Paycheck salary;
    private Syndicate syndicate;

    public Employee(String name, String address, boolean filiated)
    {
        this.name = name;
        this.address = address;
        this.filiated = filiated;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }

    public void setFiliated(boolean filiated) {
        this.filiated = filiated;
    }

    public boolean getFiliated() {
        return filiated;
    }

    public void setSalary(Paycheck salary)
	{
		this.salary = salary;
	}
    
    public Paycheck getSalary()
	{
		return this.salary;
	}

    public void setSyndicate(Syndicate syndicate)
	{
		this.syndicate = syndicate;
	}
    
    public Syndicate getSyndicate()
	{
		return this.syndicate;
	}

}