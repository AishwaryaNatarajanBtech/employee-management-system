import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import java.nio.Buffer;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

class EmployeeService {
    //private List<Employee> employees;
    private Map<Integer, Employee> employeeMap;

    public EmployeeService() {
        //this.employees = new ArrayList<>();
        this.employeeMap = new HashMap<>();
    }

    public static void validateSalary(double salary) throws InvalidEmployeeDataException {
        if (salary < 0) {
            throw new InvalidEmployeeDataException("Salary cannot be negative");
        }
    }

    public static void validateName(String name) throws InvalidEmployeeDataException {
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Name cannot be null or empty");
        }
    }

    public static void validateDepartment(String department) throws InvalidEmployeeDataException {
        if (department == null || department.isEmpty() || department.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Department cannot be null or empty");
        }
    }

    public void add(Employee employee) throws DuplicateEmployeeException {
        //employees.add(employee);

        if(employeeMap.containsKey(employee.getId())) {
            throw new DuplicateEmployeeException("Employee with id " + employee.getId() + " already exists");
        }

        employeeMap.put(employee.getId(), employee);
    }

    public void saveEmployeesToFile() {
        //code to save employees to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            //time in yyyy-MM-ddTHH:mm:ss+00:00 format
            String currentTime = java.time.ZonedDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
            writer.write("Employee data saved at: " + currentTime);
            writer.newLine();
            for (Employee employee : employeeMap.values()) {
                writer.write(employee.toString());
                writer.newLine();
            }
            System.out.println("Employees saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void viewAllEmployees() {
        employeeMap.values().forEach(e -> System.out.println(e));
    }

    public Optional<Employee> get(int id) {
        Optional<Employee> employeeOpt = Optional.ofNullable(employeeMap.get(id));
        return employeeOpt;
    }

    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOpt = get(id);
        if (employeeOpt.isPresent()) {
            return employeeOpt.get();
        } else {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
    }


    public List<Employee> filterEmployeesBySalary(double minSalary) {
        return employeeMap.values().stream()
                .filter(e -> e.getSalary() > minSalary)
                .collect(Collectors.toList());
    }

    public void printUpperCaseNames() {
        employeeMap.values().stream().map(e -> e.getName().toUpperCase()).forEach(name -> System.out.println(name));
    }

    public List<Employee> sortEmployeeBySalary() {
        return employeeMap.values().stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .collect(Collectors.toList());
    }

    public long getEmployeeCount() {
        return employeeMap.values().stream()
                .count();
    }

    public Optional<Employee> getMaxSalaryEmployee() {
        return employeeMap.values().stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
    }

    //group employees by department
    public Map<String, List<Employee>> groupEmployeesByDepartment() {
        return employeeMap.values().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
    }

    //count by first letter of name
    public Map<Character, Long> countEmployeesByFirstLetterOfName() {
        return employeeMap.values().stream()
                .collect(Collectors.groupingBy(e -> e.getName().charAt(0), Collectors.counting()));
    }

    //first sort by department and then by salary
    public void sortEmployeesByDepartmentAndSalary() {
        //sort by department and then by salary
        employeeMap.values().stream()
                        .sorted((e1, e2) -> e1.getDepartment().compareTo(e2.getDepartment()))
                        .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                        .forEach(System.out::println);
    }

    //highest salary employee
    public Optional<Employee> getHighestSalaryEmployee() {
        return employeeMap.values().stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
    }
}