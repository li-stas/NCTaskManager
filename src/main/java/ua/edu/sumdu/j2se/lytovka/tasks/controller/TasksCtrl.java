package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

import java.time.LocalDateTime;


public class TasksCtrl {
    private ArrayTaskList model;
    private TasksView view;

    public TasksCtrl(ArrayTaskList model, TasksView view) {
        this.model = model;
        this.view = view;
    }

    public RunEntry Menu00(int choice) {
        return (RunEntry) CtrlMenu00().getEntries().get(choice - 1);
    }

    private CtrlListRun CtrlMenu00() {
        CtrlListRun rum4Menu00 = new CtrlListRun();
        rum4Menu00.addEntry(new RunEntry(1) {
            public void run()  {
                if (model.size() == 0) {
                    //view.doSrcEmptyTask();  //view.doSayMess("test1 run\n");
                } else {
                    //view.doSayMess("test1 run\n");
                    int nTaskNum = view.readWhatTaskNumber(model.size());
                    if (nTaskNum != 0) {

                        //Task tmp = model.getTask(nTaskNum - 1);
                        Task tmp = ctrlCloneTask(nTaskNum);

                        while (true) {
                            Integer choice = view.menuReadTast(model.getTask(nTaskNum - 1));
                            if (choice == 0) {
                                // записать или продолжить...
                                break;
                            }
                            if (tmp.isRepeated()){
                               RunEntry entry = (RunEntry) CtrlReadTaskRepite().getEntries().get(choice - 1);
                               entry.run();
                            } else {
                                RunEntry entry = (RunEntry) CtrlReadTask().getEntries().get(choice - 1);
                                entry.run();
                            }

                            //view.doSayMess("test1 run = " + choice.toString() + "\n");
                        }
                    }
                }
            }
        });
        rum4Menu00.addEntry(new RunEntry(2) {
            public void run() {
                if (model.size() < 10) {
                    NewTask();
                } else {
                    view.doSrcMaxTasks();
                }
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

    private Task ctrlCloneTask( int nTaskNum ) {
        try {
            return model.getTask(nTaskNum - 1).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void ShowTasks() {
        Integer nSize = model.size();

        if (nSize == 0) {
            view.doSrcEmptyTasks();
        } else {
            //view.doSayMess("Всего заданий: " + nSize.toString() + "\n");
            view.doSrcTasks(model.iterator());
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
            } else { // нет - хотят продолжить
                continue;
            }
        }
    }
    public CtrlListRun CtrlReadTaskRepite() {
        CtrlListRun rum4ReadTaskRepite = new CtrlListRun();
        rum4ReadTaskRepite.addEntry(new RunEntry(1) {
            public void run() {
               view.doSayMess("test  1 run\n");
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(2) {
            public void run() {
                view.doSayMess("test2 run\n");
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(3) {
            public void run() {
                view.doSayMess("test3 run\n");
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(4) {
            public void run() {
                view.doSayMess("test4 run\n");
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(5) {
            public void run() {
                view.doSayMess("test5 run\n");
            }
        });
        return rum4ReadTaskRepite;
    }
    public CtrlListRun CtrlReadTask() {
        CtrlListRun rum4ReadTask = new CtrlListRun();
        rum4ReadTask.addEntry(new RunEntry(1) {
            public void run() {
                view.doSayMess("test=1 run\n");
            }
        });
        rum4ReadTask.addEntry(new RunEntry(2) {
            public void run() {
                view.doSayMess("test=2 run\n");
            }
        });
        rum4ReadTask.addEntry(new RunEntry(3) {
            public void run() {
                view.doSayMess("test=3 run\n");
            }
        });

        return rum4ReadTask;
    }
}
