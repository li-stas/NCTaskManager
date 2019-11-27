/**
*
* клас Task із наступними публічними методами
*
 */
package ua.edu.sumdu.j2se.lytovka.tasks;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;


public class Task implements Serializable, Cloneable {
    /**
     *
     */
    private String title; //назви задачі
    //
    private LocalDateTime time; //час виконання задачі
    //
    private LocalDateTime startTime; // початок заданим інтервалом
    private LocalDateTime endTime; // кінець заданим інтервалом
    private int interval; // заданим інтервалом (у годинах),
    private boolean repeated; //повторюваності задачі
    private boolean active; //стан задачі

    //private boolean lDEBUG = false;
    /**
     * констрктор задач
     *   що не повторюються:
     *      конструює неактивну задачу, яка
     *      виконується у заданий час без
     *      повторення iз заданою назвою
     * @param title
     * @param time
     * @throws IllegalArgumentException
     */
    public Task(String title, LocalDateTime time)  throws IllegalArgumentException {
        if (title.isEmpty() || time == null) {
            throw new IllegalArgumentException();
        }

        this.title = title;
        this.time = time;
        this.startTime = time;
        this.endTime = time;
        this.repeated = false;
        this.active = false;
    }
    /**
     * констрктор задач
     * що не повторюються c учетом активизациии
     * @param title
     * @param time
     * @param active
     */
    public Task(String title, LocalDateTime time, boolean active) {
        this.title = title;
        this.time = time;
        this.startTime = time;
        this.endTime = time;
        this.repeated = false;
        this.active = active;
    }
    /**
     * констрктор задач
     * що повторюються:
     *  конструює неактивну задачу, яка
     *  виконується  у заданому проміжку
     *  часу  (і початок і кінець включно)
     *   із заданим інтервалом і має  назву.
     * @param title
     * @param start
     * @param end
     * @param interval
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        if (start.compareTo(end) == 1) {
            throw new IllegalArgumentException();
        }
        if (title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.time = start;
        this.startTime = start;
        this.endTime = end;
        this.interval = interval;
        this.repeated = true;
        // одноазоваая задача
        if (start.equals(end)) {
            this.repeated = false;
            this.interval = 0;
        }
        this.active = false;
    }

    /**
     * констрктор задач
     * що повторюються: c установкой активности
     * @param title
     * @param start
     * @param end
     * @param interval
     * @param active
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval, boolean active) {
        this.title = title;
        this.time = start;
        this.startTime = start;
        this.endTime = end;
        this.interval = interval;
        this.repeated = true;
        // одноазоваая задача
        if (start.equals(end)) {
            this.repeated = false;
            this.interval = 0;
        }
        this.active = active;
    }
    /**
     *  загальні методи для задач
     *    ●	 Методи зчитування та встановлення
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * ●	Метод для перевірки повторюваності задачі boolean isRepeated().
     * @return
     */
    public boolean isRepeated() {
        return repeated;
    }
    /**
     *  ●	Методи для зчитування та встановлення стану задачі: boolean isActive(), void setActive(boolean active).
     * @return
     */
    public boolean isActive() {
        return active;
    }
    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
     *  що не _повторюються_:
     *     Методи для зчитування та зміни часу виконання для задач
     *     - у разі, якщо задача _повторюється_ метод має повертати час початку повторення;
     * @return
     */
    public LocalDateTime getTime() {
        if (isRepeated()) {
            return startTime;
        }
        return time;
    }
    /**
     *  - у разі, якщо задача повторювалась, вона має стати такою, що не повторюється.
     * @param time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
        this.startTime = this.endTime = time;
        this.interval = 0;
        if (isRepeated()) {
            this.repeated = false;
        }
    }
    /**
     *  що повторюються:
     *     ●	Методи для зчитування та зміни часу виконання для задач
     *     o	int getStartTime(), у разі, якщо задача _не повторюється_ метод має повертати
     *      час виконання задачі;
     * @return
     */
    public LocalDateTime getStartTime() {
        if (!isRepeated()) {
            return time;
        }
        return startTime;
    }
      /**
     * o	int getEndTime(), у разі, якщо задача _не повторюється_ метод має повертати
     *   час виконання задачі;
     * @return
     */
    public LocalDateTime getEndTime() {
        if (!isRepeated()) {
            return time;
        }
        return endTime;
    } /**
     * 	int getRepeatInterval(), інтервал повторюваності. у разі,
     *      якщо задача _не повторюється_ метод має повертати 0;
     * @return
     */
    public int getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        }
        return interval;
    }
     /**
     * o	void setTime(int start, int end, int interval), у разі, якщо задача
     *      _не повторювалася_ метод має стати такою, що повторюється.
     * @param start
     * @param end
     * @param interval
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        this.time = start;
        this.startTime = start;
        this.endTime = end;
        this.interval = interval;
        this.repeated = true;
        // одноазоваая задача
        if (start.equals(end)) {
            this.repeated = false;
            this.interval = 0;
        }
    }

    /**
     * повертає час наступного виконання задачі після вказаного часу current, якщо після вказаного часу задача
     * не виконується, то метод має повертати -1.
     * @param current
     * @return
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        //System.out.println("task"+this);
        //System.out.println( current);
        LocalDateTime currentTime;
        if (active) { // активна
            if (!repeated) { // одно разовая
                if (current.compareTo(time) == -1) { // только время ДО начала, если начло то уже -1
                    return time;
                }
                return null;
            } else { // повторяется
                //if (current < startTime) { // время до страта
                if (current.compareTo(startTime) == -1) { // время до страта
                    return startTime;
                } else {
                    int i = 0;
                    while (true) { // следующие после заданого
                        currentTime = startTime.plusSeconds(interval * i);
                        if (currentTime.compareTo(current) == 1) {
                            break;
                        }
                        i++;
                    }
                    if (currentTime.compareTo(endTime) == 1) {
                        return null;
                    }
                    return currentTime;
                }
            }
        }
        // не активна
         return null;
    }
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String toString;
        toString = "\nTask{"
                + "title='" + title + '\''
                + ", active=" + active
                + ", repeated=" + repeated;
        if (true) {
            //if (repeated) {
            toString += ", startTime=" + startTime
                    + ", endTime=" + endTime
                    + ", interval=" + interval;
            //} else {
            toString += ", time=" + time;
            //}
            toString += '}';
        }
        return toString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // ссылки ==
        if (!(o instanceof Task)) return false; // o - объект создан на основе своего класса
        Task task = (Task) o;
        return repeated == task.repeated &&
                active == task.active &&
                time.equals(task.time) &&
                startTime.equals(task.startTime) &&
                endTime.equals(task.endTime) &&
                interval == task.interval &&
                Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, repeated, active, time, startTime, endTime, interval);
    }

    /**
     * https://habr.com/ru/post/246993/
     * https://javarush.ru/quests/lectures/questmultithreading.level01.lecture07
     * https://webcache.googleusercontent.com/search?q=cache:n7q2Jn3mnEkJ:https://javarush.ru/quests/lectures/questmultithreading.level01.lecture07+&cd=2&hl=ru&ct=clnk&gl=ua
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

    /**
     * cloneStream()
     * https://javarush.ru/groups/posts/2022-serializacija-i-deserializacija-v-java
     * @return
     */
    public Task cloneStream() {
        try {
            return cloneTask();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Task cloneTask() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);
        // задачу  объекта в поток байтов
        ous.writeObject(this);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        //эксгумация потока в Задачу
        //Task cloneTask = (Task) ois.readObject();
        return (Task) ois.readObject();
    }

}
