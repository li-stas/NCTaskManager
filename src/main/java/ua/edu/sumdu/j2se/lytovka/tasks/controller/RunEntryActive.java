package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 * запрос Актиности задания
 */
public class RunEntryActive {
    private TasksView view;
    private Task tempTask;

    /**
     * Запрос и ввод информации о Активности задания
     * также запись во временный класс Задания
     * @param view
     * @param tempTask
     */
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
