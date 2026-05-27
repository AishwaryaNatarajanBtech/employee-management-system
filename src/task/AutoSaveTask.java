package task;
import service.EmployeeService;

public class AutoSaveTask implements Runnable {
    private EmployeeService employeeService;

    public AutoSaveTask(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000); // Sleep for 5 seconds
                employeeService.saveEmployeesToFile();
                System.out.println("Auto-saved employees to file. Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("AutoSaveTask interrupted: " + e.getMessage());
                break; // Exit the loop if interrupted
            }
        }
    }
}
