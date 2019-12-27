package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;


public class RunEntryActive {
    private TasksView view;
    private Task tempTask;

    public RunEntryActive(TasksView view, Task tempTask) {
        //////     задача Активнва?
        this.view = view;
        this.tempTask = tempTask;
        while (true) {
            int nActive = view.readIsTaskActive(); // задача Активнва?
            if (nActive == 0) {
                break;
            } else {
                tempTask.setActive(nActive == 1);
                break;
            }
        }
    }

}
