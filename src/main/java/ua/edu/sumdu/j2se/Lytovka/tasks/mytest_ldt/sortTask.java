package ua.edu.sumdu.j2se.lytovka.tasks.mytest_ldt;

import ua.edu.sumdu.j2se.lytovka.tasks.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.Tasks;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class sortTask {
    public static void main(String[] args) {


        setCursorPorision(0, 10);
                System.out.println((char)27 + "[30m"+"ЧЕРНЫЙ");
        setCursorPorision(1, 10);
                System.out.println((char)27 + "[31mКРАСНЫЙ");
                System.out.println((char)27 + "[32mЗЕЛЕНЫЙ");
                System.out.println((char)27 + "[33mЖЕЛТЫЙ");
                System.out.println((char)27 + "[34mСИНИЙ");
                System.out.println((char)27 + "[35mПУРПУРНЫЙ");
                System.out.println((char)27 + "[36mБИРЮЗОВЫЙ");
                System.out.println((char)27 + "[37mБЕЛЫЙ");
        setCursorPorision(0, 10);
                System.out.println((char)27 + "[40m");
                System.out.println((char)27 + "[41m");
                System.out.println((char)27 + "[42m");
                System.out.println((char)27 + "[43m");
                System.out.println((char)27 + "[44m");
                System.out.println((char)27 + "[45m");
                System.out.println((char)27 + "[46m");
                System.out.println((char)27 + "[47m");



        System.exit(999);
        /*
        java.lang.AssertionError: Missing tasks found: [
        Task{title:'A',time:2019-11-27T00:00:00.223195200,startTime:2019-11-27T00:00:00.223195200,endTime:2019-11-27T00:00:00.223195200,repeatInterval:0,active:false},
        Task{title:'B',time:2019-11-27T00:00:00.223195200,startTime:2019-11-27T00:00:00.223195200,endTime:2019-11-28T00:00:00.223195200,repeatInterval:3600,active:false},
        Task{title:'C',time:2019-11-27T00:00:00.223195200,startTime:2019-11-27T00:00:00.223195200,endTime:2019-11-27T00:00:00.223195200,repeatInterval:0,active:true},
        Task{title:'B',time:2019-11-27T00:00:00.223195200,startTime:2019-11-27T00:00:00.223195200,endTime:2019-11-28T00:00:00.223195200,repeatInterval:3600,active:true}]

	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.assertTrue(Assert.java:41)
         */
        LocalDateTime  dt = LocalDateTime.now();
        System.out.println(dt);
        //long nMiliSec = dt.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
        ZoneId zoneId = ZoneId.systemDefault();
        long nMiliSec = dt.atZone(zoneId).toInstant().toEpochMilli();
        System.out.println(nMiliSec +"\n");
        LocalDateTime nMiliSec2dt = Instant.ofEpochMilli(nMiliSec).atZone(zoneId).toLocalDateTime();;
        System.out.println(nMiliSec2dt +"\n");
        System.exit(999);

        String cdt = String.valueOf(dt);
        System.out.println(dt);
        System.out.println(cdt);
        LocalDateTime localDateTime = LocalDateTime.parse(cdt, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("");
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = LocalDateTime.parse(cdt); //, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("");
        System.out.println(localDateTime1);
        System.exit(999);

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

    private static void f(Task task1) {
        //task1.setTitle("ABC");
        LocalDateTime dNow = LocalDateTime.now();
        Task task2 = new Task("ABC",  dNow.plusSeconds(10),true);
        task1 = task2;
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


    public static void setCursorPorision(int x, int y) {
        final char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%df", escCode, y, x));
    }
}
