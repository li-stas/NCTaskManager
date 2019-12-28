package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 * редактирование Актиности задания
 */
public class RunEntryActive {

    /**
     * GET/READ  о Активности задания
     * также запись во временный класс Задания
     * @param view
     * @param tempTask
     */
    public RunEntryActive(TasksView view, Task tempTask) {
        //////     задача Активнва?
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
