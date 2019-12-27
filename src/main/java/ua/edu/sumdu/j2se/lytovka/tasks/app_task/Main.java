package ua.edu.sumdu.j2se.lytovka.tasks.app_task;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.RunEntry;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.TasksCtrl;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;


public class Main {
    public static void main(String[] args) {
        final Logger log = Logger.getLogger(Main.class.getName());
        log.info("Start! Старт!");
        doMngTask();
    }

    private static void doMngTask() {
        int choice;

        ArrayTaskList model = new ArrayTaskList();
        TasksView view = new TasksView();
        TasksCtrl ctrl = new TasksCtrl(model, view);

        Thread thr = new Thread(() -> ctrl.ChkRunTask(Thread.currentThread()));
        thr.setDaemon(true);
        thr.start();

        ctrl.TaskIO_read();

        while (true) {

            ctrl.ShowTasks();  // вывести список задач
            choice = view.menu00();
            if (choice == 0) {
                //thr.stop();                //ctrl.setlChkRunTask(false);
                break;
            }
            RunEntry entry = ctrl.Menu00(choice); // выбор методов
            entry.run();
            ctrl.TaskIO_wite();

        }
    }
}
