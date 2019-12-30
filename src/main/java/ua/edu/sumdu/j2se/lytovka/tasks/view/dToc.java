package ua.edu.sumdu.j2se.lytovka.tasks.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dToc {
    public String get_dToC(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return  now == null ? "null" : now.format(formatter);
    }
}
