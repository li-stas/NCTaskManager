package ua.edu.sumdu.j2se.lytovka.tasks.view.screens;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.toStringTaskShort;

import java.time.LocalDateTime;
import java.util.Iterator;

public class doSrcTasks {
    public void Screen(Iterator<Task> iterator) {
        System.out.print("\n");
        int i = 0;
        while (iterator.hasNext()) {
            Task t = iterator.next();
            String cMess = String.format("%2d", ++i)
                    + ". [ "
                    + (t.isActive() ? (char)27 + "[30m" : "")
                    + toStringTaskShort(t.getTitle(), t.getTime(), t.getStartTime(), t.getEndTime(),
                    t.getRepeatInterval(), t.isRepeated(), t.isActive())
                    +  (char)27 + "[37m"
                    + " ]";
            System.out.print(cMess + '\n');
        }
        System.out.print("\n");
    }
    private String toStringTaskShort(String title, LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime,
                                     int interval, boolean repeated, boolean active) {
        return new toStringTaskShort().Screen( title,  time,  startTime,  endTime,
         interval,  repeated,  active);
    }
}
