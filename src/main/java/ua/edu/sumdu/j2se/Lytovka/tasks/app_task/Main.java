package ua.edu.sumdu.j2se.lytovka.tasks.app_task;

import ua.edu.sumdu.j2se.lytovka.tasks.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.RunEntry;
import ua.edu.sumdu.j2se.lytovka.tasks.controller.TasksCtrl;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;


public class Main {
    public static void main(String[] args)  {
        /*Scanner scan = new Scanner(System.in);
        MenuMain.processChoice();
        MenuMain.nChoice(scan);*/
       doMngTask();

    }
    private static void doMngTask()  {
        int choice;
       ArrayTaskList model = new ArrayTaskList();
        TasksView view = new TasksView();
        TasksCtrl ctrl = new TasksCtrl( model, view );

        while (true) {

         ctrl.ShowTasks(); // вывести список задач

            choice = view.menu00();
            //System.out.printf("choice=%d\n",choice);
            if (choice == 0){
                break;
            }
            //RunEntry entry = (RunEntry) ctrl.CtrlMenu00().getEntries().get(choice - 1);
            //entry.run();
            ((RunEntry) ctrl.CtrlMenu00().getEntries().get(choice - 1)).run();
        }
     }
}
