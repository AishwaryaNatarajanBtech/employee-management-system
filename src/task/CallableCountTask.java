package task;
import java.util.concurrent.Callable;

import service.EmployeeService;

public class CallableCountTask implements Callable<Long> {
    EmployeeService service;

    public CallableCountTask(EmployeeService service) {
        this.service = service;
    }

    @Override
    public Long call() throws Exception {
        while(true) {
            try {
                Thread.sleep(10000);
                long count = service.getEmployeeCount();
                System.out.println("Current employee count from callable: " + count); 
                System.out.println("Thread: "+ Thread.currentThread().getName());
                return count;
            } catch (InterruptedException e) {
                System.out.println("Error in CallableCountTask: " + e.getMessage());
                throw e; // Rethrow the exception to signal that the task was interrupted
            }
        }
    }
}
