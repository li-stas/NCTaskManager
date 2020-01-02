package ua.edu.sumdu.j2se.lytovka.tasks.view.lib;

import java.time.LocalDateTime;

/**
 * фармирование строки развернутой формы задания
 */
public class toStringTask {
    public String Sreen(String title, LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime,
                        int interval, boolean repeated, boolean active) {
        return "Задание: " + String.format("%-35s", title) + (
                (!repeated) ? ("\n Время: " + dToC(time) + "\n") :
                        "\n Время начала: " + dToC(startTime) + "\n Время окончания: " + dToC(endTime) + "\n"
                                + " Интервал повторения: " + intervalHHMM(interval) + "\n"
        );
    }
    private String dToC(LocalDateTime dateTime) {
        return new dToc().get_dToC(dateTime);
    }
    private String intervalHHMM(int interval) {
        return new intervalHHMM().get_intervalHHMM(interval);
    }
}
