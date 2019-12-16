package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

import java.time.LocalDateTime;


public class TasksCtrl {
    private ArrayTaskList model;
    private TasksView view;
    private CtrlListRun rum4Menu00 = CtrlMenu00();

    public TasksCtrl(ArrayTaskList model, TasksView view) {
        this.model = model;
        this.view = view;
    }

    public RunEntry Menu00(int choice) {
        return (RunEntry) CtrlMenu00().getEntries().get(choice - 1);
    }

    public CtrlListRun CtrlMenu00() {
        CtrlListRun rum4Menu00 = new CtrlListRun();
        rum4Menu00.addEntry(new RunEntry(1) {
            public void run() {
                if (model.size() == 0) {
                    //view.doSrcEmptyTask();
                    view.doSayMess("test1 run\n");
                } else {
                    view.doSayMess("test1 run\n");
                }
            }
        });
        rum4Menu00.addEntry(new RunEntry(2) {
            public void run() {
                NewTask();
            }
        });
        rum4Menu00.addEntry(new RunEntry(3) {
            public void run() {
                if (model.size() == 0) {
                    //view.doSrcEmptyTask();
                    view.doSayMess("test3 run\n");
                } else {
                    view.doSayMess("test3 run\n");
                }
            }
        });
        rum4Menu00.addEntry(new RunEntry(4) {
            public void run() {
                if (model.size() == 0) {
                    //view.doSrcEmptyTask();
                    view.doSayMess("test4 un\n");
                } else {
                    view.doSayMess("test4 un\n");
                }
            }
        });
        return rum4Menu00;
    }

    public CtrlListRun getRum4Menu00() {
        return rum4Menu00;
    }

    public void ShowTasks() {
        Integer nSize = model.size();

        if (nSize == 0) {
            view.doSrcEmptyTask();
        } else {
            view.doSayMess("Всего заданий: " + nSize.toString() + "\n");
        }


    }

    public void NewTask() {
        String title = "";         //назви задачі    //
        LocalDateTime time = LocalDateTime.now(); //час виконання задачі    //
        LocalDateTime startTime = LocalDateTime.now(); // початок заданим інтервалом
        LocalDateTime endTime = LocalDateTime.now(); // кінець заданим інтервалом
        int interval = 0; // заданим інтервалом (у годинах),
        boolean repeated = false; //повторюваності задачі
        boolean active = false; //стан задачі

        int nRepite;
        boolean lExit = false;
        int choice;

        while (true) {

            title = view.readTitle();// Название задачи:

            nRepite = view.readIsTaskRepit(); // задача повторяется?
            if (nRepite == 0) {
                lExit = true;
                break;
            } else {
                repeated = (nRepite == 1);
            }

            /*
            view.doSayMess( time.toString()+'\n');
            time = view.readLclDtTm(time);
            view.doSayMess( time.toString()+"\n");
            */

            // временные интервалы
            if (repeated) { // да - интервал с секундах, Начало - дата и время  Окончание - дата время

                startTime = view.readStartTime();
                endTime = view.readEndTime();
                interval = view.readInterval();

            } else {    // нет  Время - дата и время
                time = view.readStartTime();
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
            } else { // нет - хотят продолжить
                continue;
            }


        }
        /*
        if (!lExit) {
            view.doSayMess("Задача: " + title + "\n");
            view.doSayMess("Повтор: " + (repeated ? "Да" : "Нет") + "\n");
        }
         */

    }
}
