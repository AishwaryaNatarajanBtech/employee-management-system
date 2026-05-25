import java.util.Optional;

public static void main(String[] args) throws EmployeeNotFoundException {
    Employee emp1 = new Employee(1, "Alice", "HR", 50000);
    Employee emp2 = new Employee(2, "Bob", "IT", 60000);
    Employee emp3 = new Employee(3, "Charlie", "Finance", 55000);
    Employee emp4 = new Employee(4, "David", "IT", 70000);
    Employee emp5 = new Employee(5, "Eve", "HR", 45000);

    EmployeeService service = new EmployeeService();
    service.addEmployee(emp1);
    service.addEmployee(emp2);
    service.addEmployee(emp3);
    service.addEmployee(emp4);
    service.addEmployee(emp5);

    service.viewAllEmployees();


    service.printUpperCaseNames();
    service.filterEmployeesBySalary(55000).forEach(e -> System.out.println("Filtered Employee: " + e));

    Optional<Employee> empOpt = service.getEmployeeById(1);
    empOpt.ifPresent(e -> System.out.println("Employee found: " + e));
    //empOpt.ifPresentOrThrow(() -> new EmployeeNotFoundException("Employee not found"));
    //if employee not found throw custom exception
    empOpt.orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

    empOpt = service.getEmployeeById(10);
    //this is to learn orElse
    empOpt.orElse(null);

    empOpt.orElseGet(() -> {
        System.out.println("Employee not found");
        return null;
    });

    service.sortEmployeeBySalary().forEach(e -> System.out.println("Sorted Employee: " + e));
    System.out.println("Total Employees: " + service.getEmployeeCount());
    System.out.println("Grouped Employees: " + service.groupEmployeesByDepartment());

//    System.out.println(emp1);
//    System.out.println(emp2);
}