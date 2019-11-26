package ua.edu.sumdu.j2se.lytovka.tasks.mytest_ldt;

import ua.edu.sumdu.j2se.lytovka.tasks.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.Tasks;

import java.time.LocalDateTime;
import java.util.*;


public class sortTask {
    public static void main(String[] args) {
        Task daily = create("Daily", YESTERDAY, TOMORROW, 3600*24);
        Task hourly = create("Hourly", TODAY, TOMORROW, 3600);
        Task every3h = create("Every 3 hours", TODAY_1H, TOMORROW, 3*3600);
        Task every3h1 = create("Every 3 hours 1", TODAY_1H, TOMORROW, 2*3600);
        daily.setActive(true);
        hourly.setActive(true);
        every3h.setActive(true);
        every3h1.setActive(true);

        /*
        SortedMap<LocalDateTime, Set<Task>> timeline = new TreeMap<>();
        timeline.put(TODAY, new HashSet<>(Arrays.asList(daily, hourly)));
        timeline.put(TODAY_1H, new HashSet<>(Arrays.asList(hourly, every3h)));
        timeline.put(TODAY_2H, new HashSet<>(Collections.singletonList(hourly)));
        timeline.put(TODAY_3H, new HashSet<>(Collections.singletonList(hourly)));
        timeline.put(TODAY_4H, new HashSet<>(Arrays.asList(hourly, every3h)));

         */
        SortedMap<LocalDateTime, Set<Task>> result = Tasks.calendar(new HashSet<>(Arrays.asList(daily, hourly, every3h)), ALMOST_TODAY, TODAY_4H);
        SortedMap<LocalDateTime, Set<Task>> result1 = Tasks.calendar(new HashSet<>(Arrays.asList(daily, hourly, every3h,every3h1)), ALMOST_TODAY, TODAY_4H);

    }
    public static Task create(String title, LocalDateTime start, LocalDateTime end, int interval) {
        return new Task(title, start, end, interval);
    }
    public static final LocalDateTime NOW = LocalDateTime.now();

    public static final LocalDateTime TODAY = NOW.withHour(0).withMinute(0).withSecond(0);
    public static final LocalDateTime YESTERDAY = TODAY.minusDays(1); // вчера
    public static final LocalDateTime ALMOST_TODAY = TODAY.minusSeconds(1); //почти сегодня
    public static final LocalDateTime TOMORROW = TODAY.plusDays(1); // завтра
    public static final LocalDateTime TODAY_1H = TODAY.plusHours(1);
    public static final LocalDateTime TODAY_2H = TODAY.plusHours(2);
    public static final LocalDateTime TODAY_3H = TODAY.plusHours(3);
    public static final LocalDateTime TODAY_4H = TODAY.plusHours(4);


}
