package ua.edu.sumdu.j2se.lytovka.tasks.view;


import org.apache.log4j.Logger;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.Menu;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.MenuEntry;

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
     * @param t
     * @return номер выбранного п.меню
     */
    public int menuReadTask(Task t) {
        int nRet;
        Menu menu = new Menu(1);
        menu.addEntry(new MenuEntry(String.format("%-25s", "1 - Название") + ":" + t.getTitle(),
                true) { public void run() {} });
        menu.addEntry(new MenuEntry(String.format("%-25s", "2 - Актинвное") + ":" + (t.isActive() ? "Да" : "Нет"),
                true) {public void run() {} });
        if (t.isRepeated()) {
            menu.addEntry(new MenuEntry(String.format("%-25s", "3 - Время начала") + ":" + t.getStartTime(),
                    true) {public void run() {} });
            menu.addEntry(new MenuEntry(String.format("%-25s", "4 - Время окончания") + ":" + t.getEndTime(),
                    true) {public void run() {} });
            menu.addEntry(new MenuEntry(String.format("%-25s", "5 - Интервал повторения") + ":" + intervalHHMM(t.getRepeatInterval()),
                    true) { public void run() {}   });
        } else {
            menu.addEntry(new MenuEntry(String.format("%-25s", "3 - Время начала") + ":" + t.getStartTime(),
                    true) { public void run() {} });
        }
        log.info("run menuReadTask");
        nRet = menu.run();
        return nRet;
    }

    /**
     * Главное меню
     * фаормирование, вывод меню и выбор и возврат выбранного п. меню
     * @return номер выбранно п. меню
     */
    public int menu00() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("1 - Редактировать", true) {public void run( ) {} });
        menu.addEntry(new MenuEntry("2 - Добавить", true) { public void run() {}  });
        menu.addEntry(new MenuEntry("3 - Удалить задание", true) {public void run() {} });
        menu.addEntry(new MenuEntry("4 - Календарь на период", true) { public void run() {} });
        System.out.print("Что хотите сделать заданиями? ");
        log.info("run menu00()");
        nRet = menu.run();
        return nRet;
    }

    /**
     * Меню запроса для построения календаря
     * @return номер выбранно п. меню
     */
    public int menu04() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("1 - На 24 часа", true) { public void run() {} });
        menu.addEntry(new MenuEntry("2 - На неделю", true) { public void run() {} });
        menu.addEntry(new MenuEntry("3 - На месяц", true) { public void run() {} });
        menu.addEntry(new MenuEntry("4 - На год", true) {  public void run() {} });
        System.out.print("На какой период построить календарь? ");
        nRet = menu.run();
        return nRet;
    }

    /**
     *   GET|READ меню запроса Да/Нет/Отмена
     * @return номер выбранно п. меню
     */
    private int readYesNo() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("1 - Да", true) {public void run() {}  });
        menu.addEntry(new MenuEntry("2 - Нет", true) {public void run() {} });
        nRet = menu.run();
        return nRet;
    }

    /**
     * вывод сообщения
     * @param cMess
     */
    public void doSayMess(String cMess) {
        System.out.print(cMess);
    }

    /**
     * вывод списка задач
     * @param iterator
     */
    public void doSrcTasks(Iterator<Task> iterator) {
        doSayMess("\n");
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
            doSayMess(cMess + '\n');
        }
        doSayMess("\n");
    }

    /**
     * вывод сообщения о привышении максимального к-ва задний
     */
    public void doSrcMaxTasks() {
        doSayMess("\n");
        doSayMess((char) 27 + "[31m" + "К-во заданий ограничено 10" + (char)27 + "[37m" + "\n");
        doSayMess("\n");
    }

    /**
     * ввыод сообщения на начале выполния задания
     * @param cMess
     * @param nIntervalChk_SS
     */
    public void doSrcWarningTasks(String cMess, int nIntervalChk_SS) {
        doSayMess("\n");
        doSayMess((char) 27 + "[34m"
                + "Чз " + String.format("%4.1f", (float) nIntervalChk_SS/60)
                + " мин наступает вркмя выполнеия задания : "
                + cMess
                + (char)27 + "[37m"
                + "\n");
        doSayMess("\n");
    }

    /**
     * вывод сообщцния о пустом списке заданий
     */
    public void doSrcEmptyTasks() {
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

        System.out.println("    Календарь заданий в период с " + dToC(start) + " по " + dToC(end) + "\n");

        int i = 1;
        // перебор элементов
        for (Map.Entry<LocalDateTime, Set<Task>> item : result.entrySet()) {

            String cDt = dToC(item.getKey());
            for (Task t : item.getValue()) {
                //                         шапка
                if (i == 1) {
                    System.out.println("--------------------------------------------------------------");
                    System.out.printf("%-15s  %s\n", "Дата и время", " |            Задание");
                    System.out.println("--------------------------------------------------------------");
                }
                //                         таблица
                System.out.printf((((i % 2) == 0) ? (char) 27 + "[30m" : "") + "%s | %s" + "\n", cDt, t.getTitle());
                System.out.print((char) 27 + "[37m");

                i++;
            }
        }
        if (i == 1) {
            doSrcEmptyTasks();
        }
        System.out.printf("\n");
    }

    /**
     * GET|READ Названия задания
     * @return название задания
     */
    public String readTitle() {
        Scanner scan = new Scanner(System.in);
        doSayMess("Название задания: ");
        String title = "";
        while (title.isEmpty()) {
            title = scan.nextLine();
        }
        return title;
    }

    /**
     * GET|READ Запроса Записи Задания
     * @return 1 - записать 2- не записывать 0 - отмена
     */
    public int readDoSaveTask() {
        System.out.print("Задание записать? ");
        return readYesNo();
    }

    /**
     * GET|READ Удалени задания
     * @return 1 - записать 2- не записывать 0 - отмена
     */
    public int readDoRemoveTask() {
        System.out.print("Задание удалить? ");
        return readYesNo();
    }

    /**
     * GET|READ Запроса о "Задание Повторяется?"
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readIsTaskRepit() {
        System.out.print("Задание Повторяется? ");
        return readYesNo();
    }
    /**
     *  GET|READ  Запроса о "Задание Активно?
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readIsTaskActive() {
        System.out.print("Задание Активно? ");
        return readYesNo();
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
    private String toStringTaskShort(String title, LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime,
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
    private String dToC(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return  now == null ? "null" : now.format(formatter);
    }

    /**
     *  Преобразование интервала выраженного  сек в
     *    интервал в Часах и Минутах
     * @param time
     * @return
     */
    private String intervalHHMM(long time) {
        return String.format("%02d:%02d:%02d", time / 3600, time / 60 % 60, time % 60);
    }
 }
