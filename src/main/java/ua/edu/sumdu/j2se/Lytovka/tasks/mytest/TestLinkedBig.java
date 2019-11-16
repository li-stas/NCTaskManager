package ua.edu.sumdu.j2se.Lytovka.tasks.mytest;

import ua.edu.sumdu.j2se.Lytovka.tasks.LinkedTaskList;
import ua.edu.sumdu.j2se.Lytovka.tasks.Task;

public class TestLinkedBig {
    public static void main(String[] args) {
        LinkedTaskList tasksList = new LinkedTaskList();
        Task task1 = new Task("A", 0);
        Task task2 = new Task("B", 1);
        Task task3 = new Task("C", 2);
        tasksList.add(task1);
        tasksList.add(task2);
        tasksList.add(task3);
        tasksList.add(new Task("D", 2));
        System.out.println(tasksList);
        LinkedTaskList res = (LinkedTaskList) tasksList.incoming(0, 1000);
        System.out.println("res.size()="+res.size());

        LinkedTaskList ts = new LinkedTaskList();

        if (true) {
            ts.add(new Task("Simple IN", 55, true));
            ts.add(new Task("Simple OUT", 10, true));
            ts.add(new Task("Inactive OUT", 0, 1000, 1, false));
            ts.add(new Task("Simple bound OUT", 50, true));
            ts.add(new Task("Simple bound IN", 60, true));
        }
        ts.add(new Task("Repeat inside IN", 51, 58, 2, true));
        ts.add(new Task("Repeat outside IN", 0, 100, 5, true));
        ts.add(new Task("Repeat outside OUT", 0, 100, 22, true));
        ts.add(new Task("Repeat left OUT", 0, 40, 1, true));
        ts.add(new Task("Repeat right OUT", 65, 100, 1, true));
        ts.add(new Task("Repeat left intersect IN 1", 0, 55, 13, true));
        ts.add(new Task("Repeat left intersect IN 2", 0, 60, 30, true));
        ts.add(new Task("Repeat left intersect OUT", 0, 55, 22, true));
        ts.add(new Task("Repeat right intersect IN", 55, 100, 20, true));


        //System.out.println(ts);
        LinkedTaskList res1 = (LinkedTaskList) ts.incoming(50, 60);
        System.out.println(res1);
        System.out.println("res1.size()="+res1.size());

    }
    public static void main3(String[] args) {
        LinkedTaskList tasksList = new LinkedTaskList();
        Task task1 = new Task("A", 10);
        Task task2 = new Task("B", 20); //, 100, 20);
        Task task3 = new Task("C", 30);
        tasksList.add(task1);		//System.out.println(tasksList);
        tasksList.add(task2);		//System.out.println(tasksList +" "+ tasksList.size());
        tasksList.add(task3);
        System.out.println(tasksList);

        System.out.println(tasksList.remove(task3));
        System.out.println(tasksList);


    }

    public static void main2(String[] args) {
        Task task;
        if (false) { // одно разовая задача
            task = new Task("some", 10);
            task.setActive(true);
            System.out.printf("nextTimeAfter 0 %d %d\n", 10, task.nextTimeAfter(0));
            System.out.printf("nextTimeAfter 9 %d %d\n", 10, task.nextTimeAfter(9));
            System.out.printf("nextTimeAfter 10 %d %d\n", -1, task.nextTimeAfter(10));
            System.out.printf("nextTimeAfter 100 %d %d\n", -1, task.nextTimeAfter(100));
        }
        if (true) {
            task = new Task("some", 10, 100, 20);
            task.setActive(true);
            System.out.printf("nextTimeAfter 0 %d %d\n", 10, task.nextTimeAfter(0));
            System.out.printf("nextTimeAfter 9 %d %d\n", 10, task.nextTimeAfter(9));

            System.out.printf("nextTimeAfter 30 %d %d\n", 50, task.nextTimeAfter(30));
            System.out.printf("nextTimeAfter 40 %d %d\n", 50, task.nextTimeAfter(40));
            System.out.printf("nextTimeAfter 10 %d %d\n", 30, task.nextTimeAfter(10));

            System.out.printf("nextTimeAfter 95 %d %d\n", -1, task.nextTimeAfter(95));
            System.out.printf("nextTimeAfter 100 %d %d\n", -1, task.nextTimeAfter(100));
        }
    }
    public static void main1(String[] args) {
        Task task;
        if (false) {
            task = new Task("Task title", 42);
            System.out.println(task);
            System.out.println(task.getTitle()); //"Task title",
            System.out.println(task.isRepeated()); //f
            System.out.println(task.isActive()); //f
            System.out.println(task.getTime()); //42
            System.out.println(task.getStartTime()); //42
            System.out.println(task.getEndTime()); //42
            System.out.println(task.getRepeatInterval());// 0
        }

        task = new Task("Task title", 5, 25, 3);
        System.out.println(task);
        if (false) {
            System.out.printf("task.getTitle()=%s\n", task.getTitle()); //"Task title",
            System.out.printf("task.isRepeated()=%s\n", task.isRepeated() ? ".t." : ".f."); //f
            System.out.printf("task.isActive()=%s\n", task.isActive() ? ".t." : ".f."); //f
            System.out.printf("task.getStartTime()=%d\n", task.getStartTime());
            System.out.printf("task.getEndTime()=%d\n", task.getEndTime());
            System.out.printf("task.getRepeatInterval()=%d\n", task.getRepeatInterval());// 0
        }
        task.setTime(42);
        if (false) {
            System.out.println(task);
            System.out.printf("task.isRepeated()=%s\n", task.isRepeated() ? ".t." : ".f."); //f
            System.out.printf("task.task.getTime()=%s\n", task.getTime());
            System.out.printf("task.getStartTime()=%d\n", task.getStartTime());
            System.out.printf("task.getEndTime()=%d\n", task.getEndTime());
            System.out.printf("task.getRepeatInterval()=%d\n", task.getRepeatInterval());// 0
        }
        task.setTime(5, 25, 3);
        System.out.println(task);
        System.out.printf("task.getStartTime()=%d\n", task.getStartTime());
        System.out.printf("task.getEndTime()=%d\n", task.getEndTime());
        System.out.printf("task.getRepeatInterval()=%d\n", task.getRepeatInterval());// 0
        System.out.printf("task.task.getTime()=%s\n",task.getTime());

    }

}
