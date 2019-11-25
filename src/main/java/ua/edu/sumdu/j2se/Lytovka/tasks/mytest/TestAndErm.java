package ua.edu.sumdu.j2se.lytovka.tasks.mytest;

import ua.edu.sumdu.j2se.lytovka.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.Task;

public class TestAndErm {
    public static void main(String[] args) {
        //System.out.println(String.format("%03d", 1));
        int time1 = 1;

        ArrayTaskList listTest = new ArrayTaskList();
        for (int i = 0; i < 100; i++) {
            //Task taskTest = new Task(String.format("some%03d", i), i);
            Task taskTest = new Task("some" + i, i);
            listTest.add(taskTest);
        }

        for (int i = 99; i > 5; i--) {
            listTest.remove(listTest.getTask(i));
        }

        for (Task t: listTest) {
            System.out.println("listTest->"+t);
        }
        System.out.println("======");
        ArrayTaskList listTest1 = new ArrayTaskList();
        for (int i = 0; i < 6; i++) {
            //Task taskTest1 = new Task(String.format("some%03d", i), i);
            Task taskTest1 = new Task("some" + i, i);
            listTest1.add(taskTest1);
        }

        for(Task t: listTest1) {
            System.out.println("listTest1->"+t);
        }

        System.out.println(listTest.equals(listTest1));
    }
}
