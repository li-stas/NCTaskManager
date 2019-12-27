package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

public class RunEntryTitle {
    private TasksView view;
    private Task tempTask;

    public RunEntryTitle(TasksView view, Task tempTask) {
        this.view = view;
        this.tempTask = tempTask;
        String title = view.readTitle();
        tempTask.setTitle(title);
    }
}
