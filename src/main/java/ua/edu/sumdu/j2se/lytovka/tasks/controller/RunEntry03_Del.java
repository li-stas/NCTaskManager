package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 *  Удалить задание
 */

public class RunEntry03_Del {
    private ArrayTaskList model;
    private TasksView view;

    public RunEntry03_Del(ArrayTaskList model, TasksView view) {
        this.model = model;
        this.view = view;
        if (model.size() != 0) {
            int nTaskNum = view.readWhatTaskNumber(model.size());
            if (nTaskNum != 0) {
                int choice = view.readDoRemoveTask();
                if (choice == 1) { // удалить
                    Task orig = model.getTask(nTaskNum - 1);
                    model.remove(orig);
                }
            }
        }

    }
}
