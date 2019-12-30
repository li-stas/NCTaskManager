package ua.edu.sumdu.j2se.lytovka.tasks.controller.get_read4edit_menu;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

import java.time.LocalDateTime;

/**
 * Редактрирование Конечной даты
 */

public class RunEntryEndTime {
    /**
     * GET/READ Конечной даты
     * @param view
     * @param tempTask
     */
    public RunEntryEndTime(TasksView view, Task tempTask) {
        LocalDateTime endTime = view.readEndTime(tempTask.getStartTime());
        tempTask.setTime(tempTask.getStartTime(), endTime, tempTask.getRepeatInterval());
    }
}
