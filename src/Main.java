import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import exception.DuplicateEmployeeException;
import exception.InvalidEmployeeDataException;
import service.EmployeeService;
import task.AutoSaveTask;
import task.CallableCountTask;
import task.CountTask;

public class Main {
public static void main(String[] args) {

    EmployeeService service = new EmployeeService();
    AutoSaveTask autoSaveTask = new AutoSaveTask(service);
    CountTask countTask = new CountTask(service);
    CallableCountTask callableCountTask = new CallableCountTask(service);
    //Thread autoSaveThread = new Thread(autoSaveTask);
    ExecutorService executor = Executors.newFixedThreadPool(3);
    try
    {
        service.loadEmployeesFromFile();
        service.viewAllEmployees();

        executor.submit(autoSaveTask);
        executor.submit(countTask);

        Future<Long> futureCount = executor.submit(callableCountTask);
        futureCount.get(); // This will block until the CallableCountTask returns a result
        //autoSaveThread.start();
        /*
        Employee emp1 = new Employee(1, "Alice", "HR", 50000);
        Employee emp2 = new Employee(2, "Bob", "IT", 60000);
        Employee emp3 = new Employee(3, "Charlie", "Finance", 55000);
        Employee emp4 = new Employee(4, "David", "IT", 70000);
        Employee emp5 = new Employee(5, "Eve", "HR", 45000);
        */
//        Employee emp6 = new Employee(5, "Eve", "HR", -4);
//        Employee emp7 = new Employee(6, "   ", "HR", 45000);
//        Employee emp8 = new Employee(7, "Frank", "", 45000);

        /* 
        service.add(emp1);
        service.add(emp2);
        service.add(emp3);
        service.add(emp4);
        service.add(emp5);
        */
       // service.add(emp5);
        
        //service.saveEmployeesToFile();

        //service.loadEmployeesFromFile();
        // service.viewAllEmployees();
    } catch (DuplicateEmployeeException e) {
        System.out.println(e.getMessage());
    } catch (InvalidEmployeeDataException e) {
        System.out.println(e.getMessage());
    } catch (InterruptedException e) {
        System.out.println("CallableCountTask interrupted: " + e.getMessage());
    } catch (ExecutionException e) {
        System.out.println("Error executing CallableCountTask: " + e.getCause().getMessage());
    } catch (Exception e) {
        System.out.println("Error in CallableCountTask: " + e.getMessage());
    }finally {
        executor.shutdownNow();
    }


    //service.viewAllEmployees();

    //service.printUpperCaseNames();
    //service.filterEmployeesBySalary(55000).forEach(e -> System.out.println("Filtered Employee: " + e));

    /*
    try
    {
        Employee employee = service.getEmployeeById(10);
        System.out.println("Employee found: " + employee);
    } catch (EmployeeNotFoundException e) {
        System.out.println(e.getMessage());
    }
    */
    
    /*
    Employee employee = service.getEmployeeById(1);
    System.out.println("Employee found: " + employee);
    */
    //if employee not found throw custom exception
    //Employee employee = service.getEmployeeById(10); // This will throw EmployeeNotFoundException if not found

    //Employee employee = service.getEmployeeById(10);
    //this is to learn orElse
    //empOpt.orElse(null);

    /*empOpt.orElseGet(() -> {
        System.out.println("Employee not found");
        return null;
    });

    service.sortEmployeeBySalary().forEach(e -> System.out.println("Sorted Employee: " + e));
    System.out.println("Total Employees: " + service.getEmployeeCount());
    System.out.println("Grouped Employees: " + service.groupEmployeesByDepartment());
    service.sortEmployeesByDepartmentAndSalary();
    System.out.println("Count by First Letter of Name: " + service.countEmployeesByFirstLetterOfName());
    System.out.println("Max Salary Employee: " + service.getMaxSalaryEmployee().orElse(null));
    */

//    System.out.println(emp1);
//    System.out.println(emp2);
}
}