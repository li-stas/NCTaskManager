package ua.edu.sumdu.j2se.Lytovka.tasks.mytest;

import ua.edu.sumdu.j2se.Lytovka.tasks.LinkedTaskList;
import ua.edu.sumdu.j2se.Lytovka.tasks.Task;

import java.util.stream.Stream;

public class TestLinked {
    public static void main(String[] args) throws CloneNotSupportedException {
        boolean lEquals;

        // Task task10 = new Task();

        LinkedTaskList tasksList = new LinkedTaskList();
        Task task1 = new Task("A", 0);
        Task task2 = new Task("B", 1);
        Task task3 = new Task("C", 2);
        tasksList.add(task1);
        tasksList.add(task2);
        tasksList.add(task3);

        // клонирование
        // LinkedTaskList tskClone =  tasksList.cloneStream();
        LinkedTaskList tskClone =  tasksList.clone();
        System.out.println("tasksList->"+tasksList);
        System.out.println("tskClone->"+tskClone);
        System.out.println("");
        tskClone.getTask(0).setTitle("A1");
        tskClone.getTask(1).setTitle("B1");
        tskClone.getTask(2).setTitle("C1");
        System.out.println("tasksList="+tasksList);
        System.out.println("tskClone="+tskClone);

        Stream<Task> stream = tskClone.getStream();
        System.out.println("Stream<Task> stream");
        stream.forEach(System.out::println);
        System.exit(999);

        if (false) {
            LinkedTaskList a = new LinkedTaskList();
            LinkedTaskList b = new LinkedTaskList();
            lEquals = a.equals(b);
            System.out.println("lEquals=" + lEquals);
            lEquals = a.equals(null);
            System.out.println("lEquals=" + lEquals);
            System.exit(999);
        }

        Task task11 = new Task("A", 0);
        Task ts11 =  task11.clone();

        lEquals = task11.equals(ts11);
        System.out.println("lEquals="+lEquals);

        System.out.println(task11);
        System.out.println(ts11);
        ts11.setTitle("A1");
        System.out.println("-"+task11);
        System.out.println("-"+ts11);

        System.exit(999);



        //LinkedTaskList
                tasksList = new LinkedTaskList();
        if (true) {
            lEquals = tasksList.equals(null);
            System.out.println("lEquals="+lEquals);
        }

        //Task
        task1 = new Task("A", 0);
        task2 = new Task("B", 1);
        task3 = new Task("C", 2);
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
