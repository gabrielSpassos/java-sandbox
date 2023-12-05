package org.gabrielspassos.task;

import org.gabrielspassos.dto.TaskDTO;
import org.gabrielspassos.external.Task;

import java.time.LocalDate;
import java.util.Random;

//todo: have name to task
public class TaskWithDelay implements Task {

    private static final int FIVE_HUNDRED_MILLISECONDS = 500;

    private static final int ZERO_SECONDS = 0;

    private final String taskName;
    private final Random random;

    public TaskWithDelay() {
        this.taskName = "Task with Delay";
        this.random = new Random();
    }

    public TaskWithDelay(Integer taskNumber) {
        this.taskName = "Task with Delay #" + taskNumber;
        this.random = new Random();
    }

    @Override
    public void execute() {
        int random = 0;
        try {
            random = random();
            Thread.sleep(random);
        } catch (Exception e) {
            System.out.println("With on the delay task " + e);
        }
        TaskDTO taskDTO = new TaskDTO(String.format("Delay of %ss", random), LocalDate.now());
        System.out.println(taskName + " with result: " + taskDTO);
    }

    private Integer random() {
        return random.nextInt(FIVE_HUNDRED_MILLISECONDS - ZERO_SECONDS) + ZERO_SECONDS;
    }

}
