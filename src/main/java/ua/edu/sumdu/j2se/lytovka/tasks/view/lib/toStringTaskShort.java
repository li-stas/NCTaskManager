package ua.edu.sumdu.j2se.lytovka.tasks.view.lib;

import java.time.LocalDateTime;

/**
 * * фармирование строки короткой формы задания
 */
public class toStringTaskShort {
    public String Screen(String title, LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime,
                                  int interval, boolean repeated, boolean active){

            return "Зд: " + String.format("%-15s", title) + (
                    (!repeated) ? (" Вp : " + dToC(time)) :
                            " ВpH: " + dToC(startTime) + " ВpК: " + dToC(endTime)  + " ИнП: " + intervalHHMM(interval) // + "\n"
            );
    }
    private String dToC(LocalDateTime dateTime) {
        return new dToc().get_dToC(dateTime);
    }
    private String intervalHHMM(int interval) {
        return new intervalHHMM().get_intervalHHMM(interval);
    }
}
