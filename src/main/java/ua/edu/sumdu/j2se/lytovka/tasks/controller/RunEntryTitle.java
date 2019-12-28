package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 * редактирование Название задания
 */
public class RunEntryTitle {

    /**
     * GET/READ о Названии задания (запрос, ввдод и запись данных)
     * @param view
     * @param tempTask
     */
    public RunEntryTitle(TasksView view, Task tempTask) {
        String title = view.readTitle();
        tempTask.setTitle(title);
    }
}
