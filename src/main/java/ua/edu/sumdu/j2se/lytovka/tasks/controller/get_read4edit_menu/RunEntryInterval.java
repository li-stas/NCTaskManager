package ua.edu.sumdu.j2se.lytovka.tasks.controller.get_read4edit_menu;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 * Редактрирование Интервала
 */
public class RunEntryInterval {
    /**
     * GET/READ Инетервала
     * @param view
     * @param tempTask
     */
    public RunEntryInterval(TasksView view, Task tempTask) {
        tempTask.setTime(tempTask.getStartTime(), tempTask.getEndTime(), view.readInterval());
    }
}
