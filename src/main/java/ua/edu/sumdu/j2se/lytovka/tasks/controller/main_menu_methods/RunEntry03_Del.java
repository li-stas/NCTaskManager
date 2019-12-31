package ua.edu.sumdu.j2se.lytovka.tasks.controller.main_menu_methods;

import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 *  Обработка п. меную "Удалить" задание
 */

public class RunEntry03_Del {

    public RunEntry03_Del(ArrayTaskList model, TasksView view) {
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
