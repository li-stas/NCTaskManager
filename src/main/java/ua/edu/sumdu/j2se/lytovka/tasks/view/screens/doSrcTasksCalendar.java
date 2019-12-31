package ua.edu.sumdu.j2se.lytovka.tasks.view.screens;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.dToc;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class doSrcTasksCalendar {
    public  void Screen(SortedMap<LocalDateTime, Set<Task>> result, LocalDateTime start, LocalDateTime end) {
        System.out.println("    Календарь заданий в период с " + dToC(start) + " по " + dToC(end) + "\n");

        int i = 1;
        // перебор элементов
        for (Map.Entry<LocalDateTime, Set<Task>> item : result.entrySet()) {

            String cDt = dToC(item.getKey());
            for (Task t : item.getValue()) {
                //                         шапка
                if (i == 1) {
                    System.out.println("--------------------------------------------------------------");
                    System.out.printf("%-15s  %s\n", "Дата и время", " |            Задание");
                    System.out.println("--------------------------------------------------------------");
                }
                //                         таблица
                System.out.printf((((i % 2) == 0) ? (char) 27 + "[30m" : "") + "%s | %s" + "\n", cDt, t.getTitle());
                System.out.print((char) 27 + "[37m");

                i++;
            }
        }
        if (i == 1) {
            doSrcEmptyTasks();
        }
        System.out.print("\n");
    }
    private void doSrcEmptyTasks() {
        new doSrcEmptyTasks().Screen();
    }
    private String dToC(LocalDateTime dateTime) {
        return new dToc().get_dToC(dateTime);
    }
}
