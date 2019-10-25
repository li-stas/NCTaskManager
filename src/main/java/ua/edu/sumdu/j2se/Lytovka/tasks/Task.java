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

    private int startTime; // початок  заданим інтервалом
    private int endTime; //  кінець
    private int interval; // заданим інтервалом

    private int time; //час виконання задачі

    private boolean repeated; //повторюваності задачі
    private boolean active; //стану задачі

    //private int startTime; //повертати час початку виконання задачі;
    //private int endTime; //повертати час закінчення виконання задачі;
    //private int repeatInterval; //задача знаходиться в інтералі повторюваності


    //, що не повторюються:
    //конструює неактивну задачу, яка виконується у заданий час
    // без повторення із заданою назвою
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.repeated = false;
        this.active = false;
        //??this.start = this.end = time;
    }

    // , що повторюються:
    //   конструює неактивну задачу, яка виконується у заданому проміжку часу
    //   (і початок і кінець включно) із заданим інтервалом і має задану назву.
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.interval = interval;
        this.repeated = true;
        this.active = false;
    }

    // загальні методи для задач
    //●	 Методи для зчитування та встановлення назви задачі: String getTitle(), void setTitle(String title).
    final public String getTitle() {
        return title;
    }
    final public void setTitle(String title) {
        this.title = title;
    }
    // ●	Методи для зчитування та встановлення стану задачі: boolean isActive(), void setActive(boolean active).
    final public boolean isActive() {
        return active;
    }
    final public void setActive(boolean active) {
        this.active = active;
    }
    //●	Метод для перевірки повторюваності задачі boolean isRepeated().
    final public boolean isRepeated() {
        return repeated;
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
        //??this.start = this.end = time;
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
    //o	int getRepeatInterval(), інтервал повторюваності. у разі,
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

}
