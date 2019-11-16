package ua.edu.sumdu.j2se.Lytovka.tasks;

import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList { //implements Iterable  {
    //private Task[] aTask = null;
    //protected int len; // = 0;
    /**
     *  пустой конструктр.
     */
   // public AbstractTaskList() { }
    /**
     * метод, що додає до списку задачу.
     * @param task
     * @throws IllegalArgumentException
     */
    public abstract void add(Task task);
    /**
     * – метод, що видаляє задачу із списку
     * і повертає істину, якщо така задача була у списку.
     * Якщо у списку було декілька таких задач,
     * необхідно видалити одну будь-яку.
     * @param task
     * @return
     */
    public abstract boolean remove(Task task);
    /**
     *  метод, що повертає кількість задач у списку.
     * @return
     */
    public abstract int size();
    /**
     *  – метод, що повертає задачу, яка
     *  знаходиться на вказаному місці у списку,
     *  перша задача має індекс 0.
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public abstract Task getTask(int index);
    public abstract Stream<Task> getStream( );
    /**
     * знаходити, які саме задачі будуть виконані хоча б раз у деякому проміжку
     * @param from
     * @param to
     * @return
     */
    //public abstract AbstractTaskList incoming(int from, int to);
    public final AbstractTaskList incoming(int from, int to) {
        AbstractTaskList resList;
        if (getClass().getName().endsWith("LinkedTaskList")) {
            resList = new LinkedTaskList();
        } else {
            resList = new ArrayTaskList();
        }
        this.getStream().filter(t->isIncoming(t, from, to, 2)).forEach( t->resList.add(t) );
        return resList;
    }
    /**
     *  задача буде виконана  деякому проміжку?
     * @param from
     * @param to
     * @return
     */

    public boolean isIncoming(Task elem, int from, int to, int typeChk) {
        int toTime = elem.nextTimeAfter(from);
        if (elem.isActive() && toTime != -1 && toTime <= to) {
            return true;
        }
        return false;
    }
}

/*
        boolean lAdd2res = false;
        if (typeChk == 1) {
            if (!elem.isActive()) {
                return false;
            }
            if (elem.isRepeated()) {
                int testTime = 0;
                boolean lAdd = false;
                // начало попадает в анализируемый интервал from-to
                if (elem.getStartTime() > from && elem.getStartTime() < to) {
                    testTime = elem.getStartTime();
                    lAdd = true;
                } else { // точку анализа переместим в интервал from-to
                    if (elem.getStartTime() > to) { // Repeat right OUT
                        lAdd = false;
                    } else if (elem.getEndTime() < from) { //Repeat left OUT
                        lAdd = false;
                    } else {
                        int i = 0;
                        while (true) { // следующие после заданого
                            i++;
                            testTime = elem.getStartTime() + (elem.getRepeatInterval() * i);
                            if (testTime > from) {
                                lAdd = true;
                                break; // попали в интервал
                            }
                            if (testTime > to) {
                                lAdd = false;
                                break; // вышли за интервла задания
                            }
                            if (testTime >= elem.getEndTime()) {
                                lAdd = false;
                                break;
                            }
                        }
                    }
                }
                if (lAdd && testTime + elem.getRepeatInterval() <= to) {
                    lAdd2res = true;
                }
            } else {
                if (elem.getTime() > from && elem.getTime() <= to) {
                    lAdd2res = true;
                }
            }
            return lAdd2res;
        }
        return false;
 */
