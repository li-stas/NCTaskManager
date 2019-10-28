package ua.edu.sumdu.j2se.Lytovka.tasks;

import java.util.Arrays;

public class ArrayTaskList extends AbstractTaskList {
    private Task[] aTask = null;
    private int len = 0;
    /**
     *  пустой конструктр.
     */
    public ArrayTaskList() {
        this.aTask = null;
        this.len = 0;
    }
    /**
     * метод, що додає до списку задачу.
     * @param task
     * @throws IllegalArgumentException
     */
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException();
        }
        if (len == 0) {
            this.aTask = new Task[]{task};
            len++;
        } else {
            // AADD
            Task[] aTmp = new Task[len + 1]; // создаем увеличенный размер
            System.arraycopy(aTask, 0, aTmp, 0, len); // копирование в новый
            aTmp[len] = task; // обновляем последний элемент
            aTask = aTmp; // замена ссылки
            len++;
        }

    };
    /**
     * – метод, що видаляє задачу із списку
     * і повертає істину, якщо така задача була у списку.
     * Якщо у списку було декілька таких задач,
     * необхідно видалити одну будь-яку.
     * @param task
     * @return
     */
    public boolean remove(Task task) {
        if (len == 0) {
            return false; // ??
        }
        // поиск задачи cTitle
        String cTitle = task.getTitle();
        int index4Del = -1;
        for (int i = 0; i < len; i++) {
           if (cTitle.startsWith(aTask[i].getTitle())) {
               index4Del = i;
               break;
           }
        }
        // удаление выбранного и смещение елементов
        if (index4Del >= 0) {

            // ADEL(,index4Del)
            // удаление выбранного и смещение елементов
            for (int i = index4Del; i < len - 1; i++) {
                aTask[i] = aTask[i + 1];
            }
            aTask[len - 1] = null;
            /// end ADEL
            // ASIZE()
            Task[] aTmp = new Task[len - 1]; // создаем уменьшенный размер
            System.arraycopy(aTask, 0, aTmp, 0, len - 1); // копирование в новый
            aTask = aTmp; // замена ссылки
            len--;
            // end ASIZE
            return true;
        }
        return false;
    }
    /**
     *  метод, що повертає кількість задач у списку.
     * @return
     */
	public int size() {
	    return len;
    }
    /**
     *  – метод, що повертає задачу, яка
     *  знаходиться на вказаному місці у списку,
     *  перша задача має індекс 0.
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (len == 0 || index > len - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return aTask[index];
    }
    /**
     * знаходити, які саме задачі будуть виконані хоча б раз у деякому проміжку
     * @param from
     * @param to
     * @return
     */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList resList = new ArrayTaskList();
        for (Task elem : aTask) {
            if (isIncoming(elem,  from,  to)) {
                resList.add(elem);
            }
        }
        return resList;
    }
    /**
     * toString.
     */
    @Override
    public String toString() {
        return "ArrayTaskList{"
                + "aTask=" + Arrays.toString(aTask)
                + ", len=" + len
                + '}';
    }
}
/*
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList resList = new ArrayTaskList();
        for (Task elem : aTask) {
            if (!elem.isActive()) {
                continue;
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
                    resList.add(elem);
                }
            } else {
                if (elem.getTime() > from && elem.getTime() <= to) {
                    resList.add(elem);
                }
            }
        }
        return resList;
    }

 */
    /*
    public boolean isIncoming(Task elem, int from, int to) {
        boolean lAdd2res = false;
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
    } */
