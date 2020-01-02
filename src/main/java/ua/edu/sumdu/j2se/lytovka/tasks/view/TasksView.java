package ua.edu.sumdu.j2se.lytovka.tasks.view;

import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.menu00;
import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.menu04;
import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.menuReadTask;
import ua.edu.sumdu.j2se.lytovka.tasks.view.getread.*;
import ua.edu.sumdu.j2se.lytovka.tasks.view.lib.dDtTm_compareLarger0;
import ua.edu.sumdu.j2se.lytovka.tasks.view.lib.toStringTask;
import ua.edu.sumdu.j2se.lytovka.tasks.view.screens.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 *  Отображение информации о Задании
 */
public class TasksView {
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
    public void doSrcEmptyTasks() {
        new doSrcEmptyTasks().Screen();
    }

    /**
     * вывода сообщения о Ошибка ввода вывода
     */
    public void doSrcIOException() {
        new doSrcIOException().Screen();
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
       return new readTitle().getread();
    }
    /**
     * GET|READ Запроса Записи Задания
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readDoSaveTask() {
        return new readDoSaveTask().getnread();
    }
    /**
     * GET|READ Удалени задания
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readDoRemoveTask() {
        return new readDoRemoveTask().getread();
    }
    /**
     * GET|READ Запроса о "Задание Повторяется?"
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readIsTaskRepit() {
        return new readIsTaskRepit().getread();
    }
    /**
     *  GET|READ  Запроса о "Задание Активно?
     * @return 1 - Да 2- Нет 0 - отмена
     */
    public int readIsTaskActive() {
        return new readIsTaskActive().getread();
    }

    /**
     * ввод, контроль, вывод Даты и времени параметров Задания
     * @param dValid
     * @return
     */
    public static LocalDateTime readLclDtTm(LocalDateTime dValid) {
        return new readLclDtTm().getread(dValid);
    }

    /**
     * проверка и вывод сообщения о том что Одна дата, меньше другой
     * @param dDtTm
     * @param dValid
     * @return логичекое значение false если "Дата и время должна быть меньше или равна Времени окончания"
     */
    public boolean dDtTm_compareLarger0(LocalDateTime dDtTm, LocalDateTime dValid) {
        return new dDtTm_compareLarger0().valid(dDtTm, dValid);
    }

    /**
     * GET|READ - начальной даты, для переидических заданий
     * @param d4Valid
     * @return
     */
    public LocalDateTime readStartTime(LocalDateTime d4Valid) {
        return new readStartTime().getread(d4Valid);
    }

    /**
     * GET|READ - конечной даты, для переидических заданий
     * @param d4Valid
     * @return
     */
    public LocalDateTime readEndTime(LocalDateTime d4Valid) {
        return new readEndTime().getread(d4Valid);
    }

    /**
     * GET|READ - интервала повторения, для переидических заданий
     * @return
     */
    public int readInterval() {
        return new readNumSayGetValid().getread("Интервал, cек: ", 0, 60 * 60 * 24);
    }

    /**
     * GET|READ - номера задания
     * @param nMaxTask
     * @return
     */
    public int readWhatTaskNumber(int nMaxTask) {
        return new readNumSayGetValid().getread("Выбирете номер задания 0 - " + nMaxTask + " (0 - Отмена):", 0, nMaxTask);
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
        return new toStringTask().Sreen(title, time, startTime, endTime, interval, repeated, active);
    }

 }
