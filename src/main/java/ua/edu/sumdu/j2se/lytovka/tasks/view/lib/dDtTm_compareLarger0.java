package ua.edu.sumdu.j2se.lytovka.tasks.view.lib;

import java.time.LocalDateTime;

public class dDtTm_compareLarger0 {
    /**
     * проверка двух дат меньше или равна
     * @param dDtTm
     * @param dValid
     * @return
     */
    public boolean valid(LocalDateTime dDtTm, LocalDateTime dValid) {
        if (dDtTm.compareTo(dValid) > 0) {
            System.out.println((char) 27 + "[31m"
                    + "Дата и время должна быть меньше или равна Времени окончания" + (char) 27 + "[37m");
            return false;
        } else {
            return true;
        }
    }
}
