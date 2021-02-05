package ua.edu.sumdu.j2se.lytovka.tasks;


import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.*;


public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        List resList = new ArrayList();
        for (Task t : tasks) {
            if (isIncoming(t, from, to)) {
                resList.add(t);
            }
        }
        return resList;
    }
     private static boolean isIncoming(Task elem, LocalDateTime from, LocalDateTime to) {
        LocalDateTime toTime = elem.nextTimeAfter(from);
        //if (elem.isActive() && toTime != -1 && (toTime < to || toTime == to )) {
        if (elem.isActive() && toTime != null
                && (toTime.compareTo(to) == -1 || toTime.compareTo(to) == 0 )) {
            return true;
        }
        return false;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        TreeMap res = new TreeMap<>();
        for (Task t : tasks) {
            if (isIncoming(t, start, end)) {
                // размножаем задачи
                LocalDateTime timeTask = t.nextTimeAfter(start);
                while (true) {
                    if (timeTask.compareTo(start) >= 0 && timeTask.compareTo(end) <= 0) {
                        HashSet<Task> value = (HashSet<Task>) res.get(timeTask);
                        if (value == null) { //  нет значений  для ключа
                            value = new HashSet<>();
                        }
                        value.add(t);
                        res.put(timeTask, value);
                    }
                    timeTask = t.nextTimeAfter(timeTask.plusSeconds(1));
                    if (timeTask.compareTo(end) > 0) {
                        break;
                    }
                }
            }
        }
        return (SortedMap<LocalDateTime, Set<Task>>) res;
    }
}
/*
public static Iterable<Task> incoming11(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        List<Task> resList = new ArrayList<>();
        for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
            Task t = it.next();
            if (isIncoming(t, from, to)) {
                resList.add(t);
            }
        }
        return (Iterable<Task>) resList;
    }
 */