package org.gabrielspassos.task;

import org.gabrielspassos.dto.TaskDTO;
import org.gabrielspassos.external.Task;

import java.time.LocalDate;

public class TaskWithoutDelay implements Task {

    //private final String taskName;

    public TaskWithoutDelay() {
    }

//    public TaskWithoutDelay(Integer taskNumber) {
//        this.taskName = "Task without Delay #" + taskNumber;
//    }

    @Override
    public void execute() {
        TaskDTO taskDTO = new TaskDTO("No delay", LocalDate.now());
        //System.out.println(taskName + " with result: " + taskDTO);
    }

}
