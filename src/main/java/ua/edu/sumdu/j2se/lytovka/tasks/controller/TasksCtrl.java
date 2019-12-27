package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * контроллер приложения
 */

public class TasksCtrl {
    private final Logger log = Logger.getLogger(TasksCtrl.class.getName());
    private static ZoneId zoneId = ZoneId.systemDefault();
    private ArrayTaskList model;
    private TasksView view;
    private CtrlListRun ctrlReadTask =  CtrlReadTask();
    private CtrlListRun ctrlReadTaskRepite =  CtrlReadTaskRepite();
    private CtrlListRun methodContainerForMenu00 =  MethodContainerForMenu00();
    private Task tempTask;
    private boolean lChkRunTask;

    /**
     * конструктор
     * @param model
     * @param view
     */
    public TasksCtrl(ArrayTaskList model, TasksView view) {
        this.model = model;
        this.view = view;
        this.lChkRunTask = true;
        log.info("Create TasksCtrl");
    }

    /**
     * возрад медода обработки выбранного п. меню
     * @param choice
     * @return
     */
    public RunEntry MethodContainerForMenu00(int choice) {
        return (RunEntry) methodContainerForMenu00.getEntries().get(choice - 1);
    }

    /**
     * наполение контейнера методами соответвующего п. Основного меню
     * @return
     */
    private CtrlListRun MethodContainerForMenu00() {
        CtrlListRun rum4Menu00 = new CtrlListRun();
        log.info("return rum4Menu00");

        rum4Menu00.addEntry(new RunEntry(1) {
            //// РЕДАКТИРОВАНИЕ
            public void run()  {
                if (model.size() != 0) {
                    int nTaskNum = view.readWhatTaskNumber(model.size());
                    if (nTaskNum != 0) {

                        Task orig = model.getTask(nTaskNum - 1);
                        tempTask = ctrlCloneTask(nTaskNum);

                        while (true) {
                           int choice = view.menuReadTast(tempTask);
                            if (choice == 0) {
                                // записать или продолжить...
                                choice = view.readDoSaveTask();
                                if (choice == 1) { // сохранить
                                    orig.setTitle(tempTask.getTitle());
                                    orig.setActive(tempTask.isActive());
                                    if (tempTask.isRepeated()) {
                                        orig.setTime(tempTask.getStartTime(), tempTask.getEndTime(), tempTask.getRepeatInterval());
                                    } else {
                                        orig.setTime(tempTask.getTime());
                                    }
                                    break;
                                } else if (choice == 0) { // выхоод
                                    break;
                                } else { // нет - хотят продолжить
                                    continue;
                                }
                            }
                            if (tempTask.isRepeated()){
                               RunEntry entry = (RunEntry) ctrlReadTaskRepite.getEntries().get(choice - 1);
                               entry.run();
                            } else {
                                RunEntry entry = (RunEntry) ctrlReadTask.getEntries().get(choice - 1);
                                entry.run();
                            }
                        }
                    }
                }
            }
        });
        rum4Menu00.addEntry(new RunEntry(2) {
            // ////// Новая задание
            public void run() { new RunEntry02_Add( model,  view); }
        });
        rum4Menu00.addEntry(new RunEntry(3) {
            /// удалить задачу
            public void run() {new RunEntry03_Del(model, view);}
        });
        rum4Menu00.addEntry(new RunEntry(4) {
            // календарь задач
            public void run() {new RunEntry04_Calendar( model,  view); }
        });

        return rum4Menu00;
    }

    /**
     * наполение контейнера методами соответвующего п. меню Редактироания задания (переиодического)
     * @return
     */
     private CtrlListRun CtrlReadTaskRepite( ) {
        CtrlListRun rum4ReadTaskRepite = new CtrlListRun();
        rum4ReadTaskRepite.addEntry(new RunEntry(1) {
            public void run() {
                new RunEntryTitle(view,tempTask);
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(2) {
            public void run() {
                new RunEntryActive(view,tempTask);
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(3) {
            public void run() {
                // должны ввести и равную и большую
                LocalDateTime startTime = view.readStartTime(tempTask.getStartTime());
                if (view.dDtTm_compareLarger0(startTime, tempTask.getEndTime())) {
                    tempTask.setTime(startTime, tempTask.getEndTime(),tempTask.getRepeatInterval());
                }
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(4) {
            public void run() {
                // дата окончания
                LocalDateTime endTime = view.readEndTime(tempTask.getStartTime());
                tempTask.setTime(tempTask.getStartTime(), endTime,tempTask.getRepeatInterval());
            }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(5) {
            public void run() {
                // интеравал
                tempTask.setTime(tempTask.getStartTime(), tempTask.getEndTime(), view.readInterval());
            }
        });
        return rum4ReadTaskRepite;
    }

    /**
     * заполение контейнера методами соответвующего п. меню Редактироания задания (одноразового)
     * @return
     */
    private CtrlListRun CtrlReadTask( ) {
        CtrlListRun rum4ReadTask = new CtrlListRun();
        rum4ReadTask.addEntry(new RunEntry(1) {
            public void run() {
                new RunEntryTitle(view,tempTask);  // заголовок
            }
        });
        rum4ReadTask.addEntry(new RunEntry(2) {
            public void run() {
                new RunEntryActive(view,tempTask);
            }
        });
        rum4ReadTask.addEntry(new RunEntry(3) {
            public void run() {
                // время старта
                tempTask.setTime(view.readStartTime(tempTask.getTime()));
            }
        });

        return rum4ReadTask;
    }

    /**
     * чтение списка заданий с файла
     */
    public void TaskIO_read(){
        try {
            TaskIO.read(model, new FileReader("test.json"));
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException " +  "test.json", e  );
        }
    }

    /**
     * запись списка заданий в файл
     */
    public void TaskIO_wite(){
        try {
            TaskIO.write(model, new FileWriter("test.json"));
        } catch (IOException e) {
            log.error("IOException", e);
            view.doSrcIOException();
        }
    }

    /**
     * Создание времменого Задания чз клонирование
     * @param nTaskNum
     * @return
     */
    private Task ctrlCloneTask( int nTaskNum ) {
        try {
            return model.getTask(nTaskNum - 1).clone();
        } catch (CloneNotSupportedException e) {
            log.error("CloneNotSupportedException", e);
        }
        return null;
    }

    /**
     * обработка запроса -Показасть список заданий
     */
    public void ShowTasks() {
        int nSize = model.size();
        if (nSize == 0) {
            view.doSrcEmptyTasks();
        } else {
            view.doSrcTasks(model.iterator());
        }
    }

    /**
     * фоновый процесс анализа сроков начала Выполнения задания
     * @param thr
     */
    public void ChkRunTask(Thread thr) {
        int nIntervalChk_SS = 60 * 15; // интервал "Заскакое время проверять"
        int nIntervaSleep = 30; // проверки
        while (lChkRunTask) {
            try {
                thr.sleep(1000 * nIntervaSleep);
                for (Task t : model) {
                    LocalDateTime dCur = LocalDateTime.now();
                    LocalDateTime dChek;
                    LocalDateTime dTime;
                    int nIntervalChk_Cur = Math.min(t.getRepeatInterval(), nIntervalChk_SS);
                    dChek = dCur.plusSeconds(nIntervalChk_Cur);
                    dTime = t.nextTimeAfter(dCur);

                    log.error("ВрТекущие dCur " + dToC(dCur)); //System.out.println("dCur " + view.dToC(dCur));
                    log.error("ВрПроверки dChek " + dToC(dChek));//  System.out.println("dChek " + view.dToC(dChek));
                    log.error("Вр_nextTimeAfter(dCur) dTime " + dToC(dTime)); //System.out.println("dTime " + view.dToC(dTime));

                    if (dTime != null) {
                        long nChek = dChek.atZone(zoneId).toInstant().toEpochMilli();
                        long nTime = dTime.atZone(zoneId).toInstant().toEpochMilli();
                        long nCtrlInt = Math.abs(nChek - nTime);
                        log.error("Math.abs(nChek - nTime)->nCtrlInt " + nCtrlInt); //System.out.println("nCtrlInt " + nCtrlInt);
                        if (nCtrlInt >= 0 && nCtrlInt <= 30000) {
                            view.doSrcWarningTasks(t.getTitle(), nIntervalChk_Cur);
                        }
                    }
                }

            } catch (InterruptedException e) {
                log.error("Interrupted Exception", e);
            }

        }
    }
    /**
     * преобразование Даты в символьную струку формата yyyy-MM-dd HH:mm
     * @param now
     * @return
     */
    private String dToC(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return  now == null ? "null" : now.format(formatter);
    }


}
