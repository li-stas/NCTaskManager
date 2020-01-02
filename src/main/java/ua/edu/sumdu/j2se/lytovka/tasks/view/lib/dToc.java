package ua.edu.sumdu.j2se.lytovka.tasks.view.lib;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dToc {
    /**
     * форматирование даты/вермени
     * @param now
     * @return
     */
    public String get_dToC(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return  now == null ? "null" : now.format(formatter);
    }
}
