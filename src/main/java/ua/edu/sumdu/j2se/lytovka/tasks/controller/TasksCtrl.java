package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.creat_list_exe_methods.CtrlListRun;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.creat_list_exe_methods.RunEntry;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.main_menu_methods.RunEntry01_Edit;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.main_menu_methods.elem_menu02_add.RunEntry02_Add;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.main_menu_methods.RunEntry03_Del;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.main_menu_methods.RunEntry04_Calendar;
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


/**
 * контроллер приложения
 */

public class TasksCtrl {
    private final Logger log = Logger.getLogger(TasksCtrl.class.getName());
    private static ZoneId zoneId = ZoneId.systemDefault();
    private ArrayTaskList model;
    private TasksView view;
    private CtrlListRun methodContainerForMenu00 =  MethodContainerForMenu00();

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
            public void run()  { new RunEntry01_Edit( model,  view);  }
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
     * обработка запроса -Показасть список заданий
     */
    public void ShowTasks() {
        int nSize = model.size();
        //view.doSayMess((char)27 + "[40m");
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
