package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

import java.time.LocalDateTime;

/**
 * Редактирования Начальной даты
 */
public class RunEntryStartTime {
    /**
     * GET/READ Начальной даты
     * @param view
     * @param tempTask
     */
    public RunEntryStartTime(TasksView view, Task tempTask) {
        // должны ввести и равную и большую
        LocalDateTime startTime = view.readStartTime(tempTask.getStartTime());
        if (view.dDtTm_compareLarger0(startTime, tempTask.getEndTime())) {
            tempTask.setTime(startTime, tempTask.getEndTime(),tempTask.getRepeatInterval());
        }
    }
}
