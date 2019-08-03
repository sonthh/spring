package demo.schedule;

import java.util.Date;

public class MySchedule {
    public void scheduleFixedDelayTask() throws InterruptedException {
        System.out.println("Task1 - " + new Date());
    }
    public void scheduleFixedRateTask() throws InterruptedException {
        System.out.println("Task2 - " + new Date());
    }
    public void scheduleTaskUsingCronExpression() throws InterruptedException {
        System.out.println("Task3 - " + new Date());
    }
}
