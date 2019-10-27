/**
*
* клас Task із наступними публічними методами
*
 */
package ua.edu.sumdu.j2se.Lytovka.tasks;

public class Task {
    /**
     *
     */
    private String title; //назви задачі
    private boolean repeated; //повторюваності задачі
    private boolean active; //стан задачі

    private int time; //час виконання задачі

    private int startTime; // початок заданим інтервалом
    private int endTime; // кінець заданим інтервалом
    private int interval; // заданим інтервалом

    //, що не повторюються:
    // конструює неактивну задачу, яка
    // виконується у заданий час без
    // повторення iз заданою назвою
    public Task(String title, int time) throws IllegalArgumentException {
        if (title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (time < 0) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.time = time;
        this.repeated = false;
        this.active = false;
        this.startTime = time;
        this.endTime = time;
    }
    public Task(String title, int time, boolean active) {
        this.title = title;
        this.time = time;
        this.repeated = false;
        this.active = active;
        this.startTime = time;
        this.endTime = time;
    }

    // , що повторюються:
    //   конструює неактивну задачу, яка
    //   виконується  у заданому проміжку
    //   часу  (і початок і кінець включно)
    //   із заданим інтервалом і має  назву.

    /**
     * c установкой активности
     *  що повторюються:
     *  конструює неактивну задачу, яка
     *  виконується  у заданому проміжку
     *  часу  (і початок і кінець включно)
     *   із заданим інтервалом і має  назву.
     * @param title
     * @param start
     * @param end
     * @param interval
     */
    public Task(String title, int start, int end, int interval) throws IllegalArgumentException {
        if (title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (start < 0 || end < 0 || start < end || interval <= 0 ) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.interval = interval;
        this.repeated = true;
        this.active = false;
        // одноазоваая задача
        if (start == end) {
            this.repeated = false;
            this.time = start;
        }
    }
    public Task(String title, int start, int end, int interval, boolean active) {
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.interval = interval;
        this.repeated = true;
        this.active = active;
        // одноазоваая задача
        if (start == end) {
            this.repeated = false;
            this.time = start;
        }
    }
    // загальні методи для задач
    //●	 Методи зчитування та встановлення
    final public String getTitle() {
        return title;
    }
    final public void setTitle(String title) {
        this.title = title;
    }
    //●	Метод для перевірки повторюваності задачі boolean isRepeated().
    final public boolean isRepeated() {
        return repeated;
    }
    // ●	Методи для зчитування та встановлення стану задачі: boolean isActive(), void setActive(boolean active).
    final public boolean isActive() {
        return active;
    }
    final public void setActive(boolean active) {
        this.active = active;
    }

    //, що не _повторюються_:
    //	Методи для зчитування та зміни часу виконання для задач
    // - у разі, якщо задача _повторюється_ метод має повертати час початку повторення;
    final public int getTime() {
        if (isRepeated()) {
            return startTime;
        }
        return time;
    }
    // - у разі, якщо задача повторювалась, вона має стати такою, що не повторюється.
    final public void setTime(int time) {
        if (isRepeated()) {
            this.repeated = false;
        }
        this.time = time;
        this.startTime = this.endTime = time;
    }

    // , що повторюються:
    //●	Методи для зчитування та зміни часу виконання для задач
    //o	int getStartTime(), у разі, якщо задача _не повторюється_ метод має повертати
    // час виконання задачі;
    final public int getStartTime() {
        if (!isRepeated()) {
            return time;
        }
        return startTime;
    }
    //o	int getEndTime(), у разі, якщо задача _не повторюється_ метод має повертати
    // час виконання задачі;
    final public int getEndTime() {
        if (!isRepeated()) {
            return time;
        }
        return endTime;
    }
    //	int getRepeatInterval(), інтервал повторюваності. у разі,
    // якщо задача _не повторюється_ метод має повертати 0;
    final public int getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        }
        return interval;
    }
    //o	void setTime(int start, int end, int interval), у разі, якщо задача
    // _не повторювалася_ метод має стати такою, що повторюється.
    final public void setTime(int start, int end, int interval) {
        if (!isRepeated()) {
            this.repeated = true;
        }
        this.startTime = start;
        this.endTime = end;
        this.interval = interval;
    }
    final public int nextTimeAfter(int current) {
        int currentTime;
        if (active) { // активна
            if (!repeated) { // одно разовая
                if (current < time) { // только время ДО начала, если начло то уже -1
                    return time;
                }
                return -1;
            } else { // повторяется
                if (current < startTime) { // время до страта
                    return startTime;
                } else {
                    currentTime = current + interval;
                    if ( currentTime >= endTime) { // выпадаем за интервал
                        return -1;
                    }
                    int i = 0;
                    while (true) { // следующие после заданого
                        i++;
                        currentTime = startTime + (interval * i);
                        if (currentTime > current) {
                            break;
                        }
                    }
                    return currentTime;
                }
            }
        }
        // не активна
         return -1;
    }

    @Override
    public String toString() {
        String toString;
        toString = "\nTask{"
                + "title='" + title + '\''
                + ", active=" + active
                + ", repeated=" + repeated;
        if (false) {
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

}
