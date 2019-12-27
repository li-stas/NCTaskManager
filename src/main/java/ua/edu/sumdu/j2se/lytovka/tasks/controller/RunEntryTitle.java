package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 * обработка Название задания
 */
public class RunEntryTitle {
    private TasksView view;
    private Task tempTask;

    /**
     * запрос, ввдод и запись данных о Названии задания
     * @param view
     * @param tempTask
     */
    public RunEntryTitle(TasksView view, Task tempTask) {
        this.view = view;
        this.tempTask = tempTask;
        String title = view.readTitle();
        tempTask.setTitle(title);
    }
}
