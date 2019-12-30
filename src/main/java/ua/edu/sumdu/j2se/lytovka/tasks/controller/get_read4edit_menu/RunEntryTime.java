package ua.edu.sumdu.j2se.lytovka.tasks.controller.get_read4edit_menu;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 * редактирование Времени для одноразовых заданий
 */
public class RunEntryTime {
    /**
     * GET/READ  Времени для одноразовых заданий
     * @param view
     * @param tempTask
     */
    public RunEntryTime(TasksView view, Task tempTask) {
        tempTask.setTime(view.readStartTime(tempTask.getTime()));
    }
}
