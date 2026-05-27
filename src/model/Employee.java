package model;
import exception.InvalidEmployeeDataException;
import service.EmployeeService;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) throws InvalidEmployeeDataException {
        this.id = id;

        EmployeeService.validateName(name);
        EmployeeService.validateDepartment(department);
        EmployeeService.validateSalary(salary);
        
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) throws InvalidEmployeeDataException {
        if(name == null || name.isEmpty() || name.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Name cannot be null or empty");
        }
        this.name = name;
    }
    
    public void setDepartment(String department) throws InvalidEmployeeDataException {
        if(department == null || department.isEmpty() || department.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Department cannot be null or empty");
        }
        this.department = department;
    }

    public void setSalary(double salary) throws InvalidEmployeeDataException {
        if (salary < 0) {
            throw new InvalidEmployeeDataException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + department + "," + salary;
    }
}