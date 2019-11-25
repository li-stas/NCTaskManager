package ua.edu.sumdu.j2se.lytovka.tasks.mytest_ldt;

import ua.edu.sumdu.j2se.lytovka.tasks.Task;

import java.time.LocalDateTime;


public class nextTmAft {
    public static void main(String[] args) {
        LocalDateTime dNow = LocalDateTime.now();
        Task task1 = new Task("some1",  dNow.plusSeconds(10),true);
        Task task2 = new Task("some1",  dNow.plusSeconds(10),true);
        System.out.println(task1.equals(task2));
        System.out.println(task1);
        System.out.println(task2);

        task2.setTime(dNow.plusSeconds(10), dNow.plusSeconds(100), 10); //сделали повторяющеся
        task2.setTime(dNow.plusSeconds(10)); // вернули состояние неповторяющейся задачи
        System.out.println(task1.equals(task2));
        System.out.println(task1);
        System.out.println(task2);
    }
}
