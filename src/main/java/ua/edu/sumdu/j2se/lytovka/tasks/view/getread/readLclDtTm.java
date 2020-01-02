package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

import java.time.LocalDateTime;

public class readLclDtTm {
    /**
     * ввод, контроль, вывод Даты и времени параметров Задания
     * @param dValid
     * @return
     */
    public LocalDateTime getread(LocalDateTime dValid) {
        LocalDateTime dDtTm = LocalDateTime.now();
        int nYYYY = 0;
        int nMM;
        int nDD;
        int nHH;
        int nMi;

        while (true) {

            nYYYY = readNumSayGetValid("    Год (ГГГГ): ", dDtTm.getYear(), dDtTm.getYear() + 50);

            nMM = readNumSayGetValid("    Месяц (ММ): ",  1, 12);

            dDtTm = LocalDateTime.of(nYYYY, nMM, 1, 0, 0, 0);

            nDD = readNumSayGetValid("    День (ДД) : ", 1, dDtTm.plusMonths(1).minusDays(1).getDayOfMonth());

            nHH = readNumSayGetValid("    Час (ЧЧ)  : ", 0, 23);

            nMi = readNumSayGetValid("    Минуты(мм): ", 0, 59);


            dDtTm = LocalDateTime.of(nYYYY, nMM, nDD, nHH, nMi, 0, 0);
            if (dDtTm_compareLess0(dDtTm, dValid)) {
                break;
            }
        }
        return dDtTm;
    }
    /**
     * проверка и вывод сообщения о том что Одна дата, меньше другой
     * @param dDtTm
     * @param dValid
     * @return логичекое значение false - "Дата и время уже прошли"
     */
    private boolean dDtTm_compareLess0(LocalDateTime dDtTm, LocalDateTime dValid) {
        if (dDtTm.compareTo(dValid) < 0) {
            System.out.println((char) 27 + "[31m" + "Дата и время уже прошли" + (char) 27 + "[37m");
            return false;
        } else {
            return true;
        }
    }

    private int readNumSayGetValid(String s1, int from, int to) {
        return new readNumSayGetValid().getread(s1, from,  to);
    }
}
