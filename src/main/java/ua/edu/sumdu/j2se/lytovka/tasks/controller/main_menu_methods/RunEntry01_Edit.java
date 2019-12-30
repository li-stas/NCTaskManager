package ua.edu.sumdu.j2se.lytovka.tasks.controller.main_menu_methods;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.creat_list_exe_methods.CtrlListRun;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.creat_list_exe_methods.RunEntry;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.TasksCtrl;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.get_read4edit_menu.*;
import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

/**
 * обработака п. меню Редактировать
 */
public class RunEntry01_Edit {
    private final Logger log = Logger.getLogger(TasksCtrl.class.getName());
    private ArrayTaskList model;
    private TasksView view;
    private Task tempTask;

    public RunEntry01_Edit(ArrayTaskList model, TasksView view) {
        this.model = model;
        this.view = view;
        if (model.size() != 0) {

            int nTaskNum = view.readWhatTaskNumber(model.size());
            if (nTaskNum != 0) {

                Task orig = model.getTask(nTaskNum - 1);
                tempTask = ctrlCloneTask(nTaskNum);

                while (true) {
                    int choice = view.menuReadTask(tempTask);
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
                        CtrlListRun ctrlReadTaskRepite = CtrlReadTaskRepite();
                        RunEntry entry = (RunEntry) ctrlReadTaskRepite.getEntries().get(choice - 1);
                        entry.run();
                    } else {
                        CtrlListRun ctrlReadTask = CtrlReadTask();
                        RunEntry entry = (RunEntry) ctrlReadTask.getEntries().get(choice - 1);
                        entry.run();
                    }
                }
            }
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
     * наполение контейнера методами соответвующего п. меню Редактироания задания (переиодического)
     * @return
     */
    private CtrlListRun CtrlReadTaskRepite() {
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
            public void run() { new RunEntryStartTime(view,tempTask); }
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(4) {
            public void run() {  new RunEntryEndTime(view,tempTask); } // дата окончания
        });
        rum4ReadTaskRepite.addEntry(new RunEntry(5) {
            public void run() {  new RunEntryInterval(view,tempTask); } // интеравал
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
            public void run() {  new RunEntryTitle(view,tempTask); } // заголовок
        });
        rum4ReadTask.addEntry(new RunEntry(2) {
            public void run() {
                new RunEntryActive(view,tempTask);
            }
        });
        rum4ReadTask.addEntry(new RunEntry(3) {
            public void run() {new RunEntryTime(view, tempTask);  } // время старта
        });

        return rum4ReadTask;
    }


}
