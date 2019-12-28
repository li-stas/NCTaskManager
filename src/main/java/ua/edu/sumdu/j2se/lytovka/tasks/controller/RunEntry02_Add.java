package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 *
 *  обработака п. меню Добавить (добавить новое задание)
 */
public class RunEntry02_Add {

    public RunEntry02_Add(ArrayTaskList model, TasksView view) {
        if (model.size() < 10) {
            new NewTask(model, view);
        } else {
            view.doSrcMaxTasks();
        }
    }
}
