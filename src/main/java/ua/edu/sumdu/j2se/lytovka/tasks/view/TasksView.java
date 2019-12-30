package ua.edu.sumdu.j2se.lytovka.tasks.view;


import org.apache.log4j.Logger;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.menu00;
import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.menu04;
import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.menuReadTask;
import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.readYesNo;
import ua.edu.sumdu.j2se.lytovka.tasks.view.getread.*;
import ua.edu.sumdu.j2se.lytovka.tasks.view.screens.doSrcMaxTasks;
import ua.edu.sumdu.j2se.lytovka.tasks.view.screens.doSrcTasks;
import ua.edu.sumdu.j2se.lytovka.tasks.view.screens.doSrcTasksCalendar;
import ua.edu.sumdu.j2se.lytovka.tasks.view.screens.doSrcWarningTasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *  Отображение информации о Задании
 */
public class TasksView {
    final Logger log = Logger.getLogger(TasksView.class.getName());

    /**
     * формирование, вывод меню и выбор и возврат выбранного п. меню
     * для редактирования задания
     * @param t - задание
     * @return номер выбранного п.меню
     */
    public int menuReadTask(Task t) {
        return new menuReadTask(t).getnRet();
    }
    /**
     * Главное меню
     * фаормирование, вывод меню и выбор и возврат выбранного п. меню
     * @return номер выбранно п. меню
     */
    public int menu00() {
        return new menu00().getnRet();
    }
    /**
     * Меню запроса для построения календаря
     * @return номер выбранно п. меню
     */
    public int menu04() {
        return new menu04().getnRet();
    }
    /**
     *   GET|READ меню запроса Да/Нет/Отмена
     * @return номер выбранно п. меню
     */
    private int readYesNo() {
        return new readYesNo().getnRet();
    }
    /**
     * вывод сообщения
     * @param cMess
     */
    public static void doSayMess(String cMess) {
        System.out.print(cMess);
    }

    /**
     * вывод списка задач
     * @param iterator
     */
    public void doSrcTasks(Iterator<Task> iterator) {
        new doSrcTasks().Screen(iterator);
    }

    /**
     * вывод сообщения о привышении максимального к-ва задний
     */
    public void doSrcMaxTasks() {
        new doSrcMaxTasks().Screen();
    }
    /**
     * ввыод сообщения на начале выполния задания
     * @param cMess
     * @param nIntervalChk_SS
     */
    public void doSrcWarningTasks(String cMess, int nIntervalChk_SS) {
        new doSrcWarningTasks().Screen( cMess, nIntervalChk_SS);
    }

    /**
     * вывод сообщцния о пустом списке заданий
     */
    public static void doSrcEmptyTasks() {
        doSayMess("\n");
        doSayMess((char) 27 + "[33m" + "Список заданий пустой"+ (char)27 + "[37m" + "\n");
        doSayMess("\n");
    }

    /**
     * вывода сообщения о Ошибка ввода вывода
     */
    public void doSrcIOException() {
        System.out.println((char) 27 + "[31m" + "Ошибка ввода вывода" + (char) 27 + "[37m");
    }

    /**
     * вывод календаря заданий на период
     * @param result список заданий
     * @param start  начальная дата
     * @param end конечная дата
     */
    public void doSrcTasksCalendar(SortedMap<LocalDateTime, Set<Task>> result, LocalDateTime start, LocalDateTime end) {
        new doSrcTasksCalendar().Screen(result,  start,  end);
    }

    /**
     * GET|READ Названия задания
     * @return название задания
     */
    public String readTitle() {
       return new readTitle().getTitle();
    }
    /**
     * GET|READ Запроса Записи Задания
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readDoSaveTask() {
        return new readDoSaveTask().getnRet();
    }
    /**
     * GET|READ Удалени задания
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readDoRemoveTask() {
        return new readDoRemoveTask().getnRet();
    }
    /**
     * GET|READ Запроса о "Задание Повторяется?"
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readIsTaskRepit() {
        return new readIsTaskRepit().getnRet();
    }
    /**
     *  GET|READ  Запроса о "Задание Активно?
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readIsTaskActive() {
        return new readIsTaskActive().getnRet();
    }

    /**
     * ввод, контроль, вывод Даты и времени параметров Задания
     * @param dValid
     * @return
     */
    private LocalDateTime readLclDtTm(LocalDateTime dValid) {
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
    /**
     * проверка и вывод сообщения о том что Одна дата, меньше другой
     * @param dDtTm
     * @param dValid
     * @return логичекое значение false если "Дата и время должна быть меньше или равна Времени окончания"
     */
    public boolean dDtTm_compareLarger0(LocalDateTime dDtTm, LocalDateTime dValid) {
        if (dDtTm.compareTo(dValid) > 0) {
            System.out.println((char) 27 + "[31m"
                    + "Дата и время должна быть меньше или равна Времени окончания" + (char) 27 + "[37m");
            return false;
        } else {
            return true;
        }
    }

    /**
     * GET|READ - начальной даты, для переидических заданий
     * @param d4Valid
     * @return
     */
    public LocalDateTime readStartTime(LocalDateTime d4Valid) {
        System.out.println("Время начала: ");
        return readLclDtTm(d4Valid);
    }

    /**
     * GET|READ - конечной даты, для переидических заданий
     * @param d4Valid
     * @return
     */
    public LocalDateTime readEndTime(LocalDateTime d4Valid) {
        System.out.println("Время оконончания: ");
        return readLclDtTm(d4Valid);
    }

    /**
     * GET|READ - интервала повторения, для переидических заданий
     * @return
     */
    public int readInterval() {
        return readNumSayGetValid("Интервал, cек: ", 0, 60 * 60 * 24);
    }

    /**
     * GET|READ - номера задания
     * @param nMaxTask
     * @return
     */
    public int readWhatTaskNumber(int nMaxTask) {
        return readNumSayGetValid("Выбирете номер задания 0 - " + nMaxTask + " (0 - Отмена):", 0, nMaxTask);
    }

    /**
     * GET|READ - числовых значений с проверкой вхождения в диапазон
     * @param s1
     * @param from
     * @param to
     * @return
     */
    private int readNumSayGetValid(String s1, int from, int to) {
        int[] paramInt = new int[1];
        System.out.print(s1);
        readNum(paramInt, from, to);
        return paramInt[0];
    }
    /**
     * ввод чисел с проверкой диапзона
     *
     * @param nNumParam
     * @param from
     * @param to
     */
    private void readNum(int[] nNumParam, int from, int to) {
        int nNum;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String line = reader.readLine();
                nNum = Integer.parseInt(line);
                if (nNum >= from && nNum <= to) {
                    nNumParam[0] = nNum;
                    break;
                } else {
                    System.out.println((char) 27 + "[31mДиапазон: "
                            + " " + from + " - " + to + (char) 27 + "[37m");
                    continue;
                }
            } catch (IOException e) {
                log.error("IOException", e);
                doSrcIOException();
                continue;
            } catch (NumberFormatException e) {
                System.out.println((char)27 + "[31m" + "Только цифры:"
                        + " " + from + " - " + to + (char)27 + "[37m");
                continue;
            }
        }
    }


    /**
     * фармирование строки развернутой формы задания
     * @param title
     * @param time
     * @param startTime
     * @param endTime
     * @param interval
     * @param repeated
     * @param active
     * @return
     */
    public String toStringTask(String title, LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime,
                               int interval, boolean repeated, boolean active) {
        return "Задание: " + String.format("%-35s", title) + (
                (!repeated) ? ("\n Время: " + dToC(time) + "\n") :
                        "\n Время начала: " + dToC(startTime) + "\n Время окончания: " + dToC(endTime) + "\n"
                                + " Интервал повторения: " + intervalHHMM(interval) + "\n"
        );
    }

    /**
     * фармирование строки короткой формы задания
     * @param title
     * @param time
     * @param startTime
     * @param endTime
     * @param interval
     * @param repeated
     * @param active
     * @return
     */
    public static String toStringTaskShort(String title, LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime,
                                           int interval, boolean repeated, boolean active) {
        return "Зд: " + String.format("%-15s", title) + (
                (!repeated) ? (" Вp : " + dToC(time)) :
                        " ВpH: " + dToC(startTime) + " ВpК: " + dToC(endTime)  + " ИнП: " + intervalHHMM(interval) // + "\n"
        );
    }
    /**
     * преобразование Даты в символьную струку формата yyyy-MM-dd HH:mm
     * @param now
     * @return
     */
    public static String dToC(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return  now == null ? "null" : now.format(formatter);
    }

    /**
     *  Преобразование интервала выраженного  сек в
     *    интервал в Часах и Минутах
     * @param time
     * @return
     */
    private static String intervalHHMM(long time) {
        return String.format("%02d:%02d:%02d", time / 3600, time / 60 % 60, time % 60);
    }
 }
