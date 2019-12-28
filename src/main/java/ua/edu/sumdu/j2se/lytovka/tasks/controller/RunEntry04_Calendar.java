package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Tasks;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;

/**
 * построение клалендаря заданий не выбранный переиод
 */

public class RunEntry04_Calendar {

    /**
     * метдот построение клалендаря заданий не выбранный переиод
     * @param model
     * @param view
     */
    public RunEntry04_Calendar(ArrayTaskList model, TasksView view) {
        if (model.size() != 0) {
            LocalDateTime start = LocalDateTime.now();
            LocalDateTime end = LocalDateTime.now();
            int choice = view.menu04();
            switch (choice){
                case 0:
                    break;
                case 1: // добавить день
                    end = start.plusHours(24);
                    break;
                case 2: // добавить неделю
                    end = start.plusDays(7);
                    break;
                case 3: // дебавидть месяц
                    end = start.plusMonths(1);
                    break;
                case 4: // добавить 12 месяцев
                    end = start.plusMonths(12);
                    break;
            }

            if (choice != 0) {
                HashSet<Task> hsIask = new HashSet<Task>();
                for (Task task : model) {
                    hsIask.add(task);
                }
                SortedMap<LocalDateTime, Set<Task>> result = Tasks.calendar(hsIask, start, end);

                view.doSrcTasksCalendar(result, start, end);

            }
        }
    }
}
