package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

import java.time.LocalDateTime;

/**
 * класс обработки ввода нового задания
 */

public class NewTask {
    private ArrayTaskList model;
    private TasksView view;

    /**
     *  ввод и контроль ввода при добавлении нового задания)
     * @param model
     * @param view
     */
    public NewTask(ArrayTaskList model, TasksView view) {
        this.model = model;
        this.view = view;
        String title = "";         //назви задачі    //
        LocalDateTime time = LocalDateTime.now(); //час виконання задачі    //
        LocalDateTime startTime = LocalDateTime.now(); // початок заданим інтервалом
        LocalDateTime endTime = LocalDateTime.now(); // кінець заданим інтервалом
        int interval = 0; // заданим інтервалом (у годинах),
        boolean repeated = false; //повторюваності задачі
        boolean active = false; //стан задачі

        int nRepite;
        int choice;

        while (true) {

            title = view.readTitle();// Название задачи:

            nRepite = view.readIsTaskRepit(); // задача повторяется?
            if (nRepite == 0) {
                break;
            } else {
                repeated = (nRepite == 1);
            }
            // временные интервалы
            if (repeated) { // да - интервал с секундах, Начало - дата и время  Окончание - дата время

                startTime = view.readStartTime(LocalDateTime.now());
                endTime = view.readEndTime(startTime);
                interval = view.readInterval();

            } else {    // нет  Время - дата и время
                time = view.readStartTime(LocalDateTime.now());
            }

            view.doSayMess(view.toStringTask(title, time, startTime, endTime, interval, repeated, active) + "\n");
            choice = view.readDoSaveTask();
            if (choice == 1) { // сохранить
                if (repeated) {
                    model.add(model.CreateTaskRepite(title, startTime, endTime, interval));
                } else {
                    model.add(model.CreateTaskOne(title, time));
                }
                break;
            } else if (choice == 0) { // выхоод
                break;
            }
        }
    }
}
