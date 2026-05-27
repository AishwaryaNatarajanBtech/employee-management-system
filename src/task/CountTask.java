package task;
import service.EmployeeService;

public class CountTask implements Runnable {
    EmployeeService service;

    public CountTask(EmployeeService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(3000);
                System.out.println("Current employee count: " + service.getEmployeeCount()); 
                System.out.println("Thread: "+ Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("Error in CountTask: " + e.getMessage());
            }
        }
    }
}
