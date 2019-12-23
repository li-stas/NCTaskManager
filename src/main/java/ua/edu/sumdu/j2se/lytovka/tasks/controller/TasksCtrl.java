package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Tasks;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


public class TasksCtrl {
    private ArrayTaskList model;
    private TasksView view;
    private CtrlListRun CtrlReadTask =  CtrlReadTask();
    private CtrlListRun CtrlReadTaskRepite =  CtrlReadTaskRepite();
    private Task tmp;

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
            //// РЕДАКТИРОВАНИЕ
            public void run()  {
                if (model.size() == 0) {
                    //view.doSrcEmptyTask();  //view.doSayMess("test1 run\n");
                } else {
                    //view.doSayMess("test1 run\n");
                    int nTaskNum = view.readWhatTaskNumber(model.size());
                    if (nTaskNum != 0) {

                        Task orig = model.getTask(nTaskNum - 1);
                        //Task tmp = model.getTask(nTaskNum - 1);
                        tmp = ctrlCloneTask(nTaskNum);


                        while (true) {
                            Integer choice = view.menuReadTast(tmp);
                            if (choice == 0) {
                                // записать или продолжить...
                                choice = view.readDoSaveTask();
                                if (choice == 1) { // сохранить
                                    if (tmp.isRepeated()) {
                                        orig.setTime(tmp.getStartTime(), tmp.getEndTime(), tmp.getRepeatInterval());
                                    } else {
                                        orig.setTitle(tmp.getTitle());
                                        orig.setTime(tmp.getTime());
                                    }
                                    orig.setActive(tmp.isActive());
                                    break;
                                } else if (choice == 0) { // выхоод
                                    break;
                                } else { // нет - хотят продолжить
                                    continue;
                                }

                            }
                            if (tmp.isRepeated()){
                               RunEntry entry = (RunEntry) CtrlReadTaskRepite.getEntries().get(choice - 1);
                               entry.run();
                            } else {
                                RunEntry entry = (RunEntry) CtrlReadTask.getEntries().get(choice - 1);
                                entry.run();
                            }

                            //view.doSayMess("test1 run = " + choice.toString() + "\n");
                        }
                    }
                }
            }
        });
        rum4Menu00.addEntry(new RunEntry(2) {
            // ////// Новая задача
            public void run() {
                if (model.size() < 10) {
                    NewTask();
                } else {
                    view.doSrcMaxTasks();
                }
            }
        });
        rum4Menu00.addEntry(new RunEntry(3) {
            /// удалить задачу
            public void run() {
                if (model.size() == 0) {
                    //
                } else {
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
        });
        rum4Menu00.addEntry(new RunEntry(4) {
            public void run() {
                if (model.size() == 0) {
                    //view.doSrcEmptyTask();
                } else {
                    // view.doSayMess("test1 choice = " + choice + "\n");
                    LocalDateTime start = LocalDateTime.now();
                    LocalDateTime end = LocalDateTime.now();
                    int choice = view.menu04();
                    switch (choice){
                        case 0:
                            break;
                        case 1: // добавить день
                            end = start.plusHours(24);
                            break;
                        case 2: // добавить неделю
                            end = start.plusDays(7);
                            break;
                        case 3: // дебавидть месяц
                            end = start.plusMonths(1);
                            break;
                        case 4: // добавить 12 месяцев
                            end = start.plusMonths(12);
                            break;
                    }
                     view.doSayMess("test1 choice = " + choice + "\n");
                     view.doSayMess("test1 choice = " + start + " " + end + "\n");

                    if (choice != 0) {
                        HashSet<Task> hsIask = new HashSet<Task>();
                        for (Iterator<Task> iterator = model.iterator(); iterator.hasNext(); ) {
                            hsIask.add(iterator.next());
                        }
                        SortedMap<LocalDateTime, Set<Task>> result = Tasks.calendar(hsIask, start, end);
                        //view.doSayMess("test1 choice = " + choice + "\n");
                        view.doSrcTasksCalendar(result);

                    }
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
               //view.doSayMess("test  1 run\n");
                String title = view.readTitle();
                tmp.setTitle(title);
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(2) {
            public void run() {
                while (true) {
                    int nActive = view.readIsTaskActive(); // задача Активнва?
                    if (nActive == 0) {
                        break;
                    } else {
                        tmp.setActive(nActive == 1);
                        break;
                    }
                }
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(3) {
            public void run() {
                // должны ввести и равную и большую
                LocalDateTime startTime = view.readStartTime(tmp.getStartTime());
                if (view.dDtTm_compare02(startTime, tmp.getEndTime())) {
                    tmp.setTime(startTime, tmp.getEndTime(),tmp.getRepeatInterval());
                } else {
                    // ошибка ничего не делаем
                }
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(4) {
            public void run() {
                // дата окончания
                LocalDateTime endTime = view.readEndTime(tmp.getStartTime());
                tmp.setTime(tmp.getStartTime(), endTime,tmp.getRepeatInterval());
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(5) {
            public void run() {
                // интеравал
                tmp.setTime(tmp.getStartTime(), tmp.getEndTime(), view.readInterval());
            }
        });
        return rum4ReadTaskRepite;
    }
    public CtrlListRun CtrlReadTask() {
        CtrlListRun rum4ReadTask = new CtrlListRun();
        rum4ReadTask.addEntry(new RunEntry(1) {
            public void run() {
                // заголовок
                String title = view.readTitle();
                tmp.setTitle(title);
            }
        });
        rum4ReadTask.addEntry(new RunEntry(2) {
            public void run() {
                // Активность
                while (true) {
                    int nActive = view.readIsTaskActive(); // задача Активнва?
                    if (nActive == 0) {
                        break;
                    } else {
                        tmp.setActive(nActive == 1);
                        break;
                    }
                }
            }
        });
        rum4ReadTask.addEntry(new RunEntry(3) {
            public void run() {
                // время старта
                tmp.setTime(view.readStartTime(tmp.getTime()));
            }
        });

        return rum4ReadTask;
    }

    public void TaskIO_read(){
        try {
            TaskIO.read(model, new FileReader("test.json"));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
        }
    }

    public void TaskIO_wite(){
        try {
            TaskIO.write(model, new FileWriter("test.json"));
        } catch (IOException e) {
            //e.printStackTrace();
            view.doSrcIOException();
        }
    }
}
