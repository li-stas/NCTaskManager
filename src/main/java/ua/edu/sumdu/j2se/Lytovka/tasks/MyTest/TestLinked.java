package ua.edu.sumdu.j2se.Lytovka.tasks.MyTest;

import ua.edu.sumdu.j2se.Lytovka.tasks.LinkedTaskList;
import ua.edu.sumdu.j2se.Lytovka.tasks.Task;

public class TestLinked {
    public static void main(String[] args) {

        LinkedTaskList a = new LinkedTaskList();
        LinkedTaskList b = new LinkedTaskList();
        boolean lEquals = a.equals(b);
        System.out.println("lEquals="+lEquals);
        System.exit(999);

        LinkedTaskList tasksList = new LinkedTaskList();
        if (true) {
            lEquals = tasksList.equals(null);
            System.out.println("lEquals="+lEquals);
        }

        Task task1 = new Task("A", 0);
        Task task2 = new Task("B", 1);
        Task task3 = new Task("C", 2);
        tasksList.add(task1);
        tasksList.add(task2);
        tasksList.add(task3);
        //tasksList.add(new Task("D", 3));
        System.out.println(tasksList);


        lEquals = tasksList.equals(null);
        System.out.println(lEquals);

        System.out.println(tasksList.remove(task1));
        System.out.println(tasksList);
        if (false) {
            System.out.println(tasksList.getTask(0));
            System.out.println(tasksList.getTask(1));
            System.out.println(tasksList.getTask(2));
            System.out.println(tasksList.getTask(4));
        }
    }
}
