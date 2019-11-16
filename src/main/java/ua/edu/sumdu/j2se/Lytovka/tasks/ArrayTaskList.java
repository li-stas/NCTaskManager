package ua.edu.sumdu.j2se.Lytovka.tasks;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList implements  Cloneable, Iterable<Task> {
    private Task[] aTask; // = null;
    private int len; // = 0;

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
            ADelAndASize(index4Del);
            /*
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
             */
            return true;
        }
        return false;
    }
    /**
     *  метод, що повертає кількість задач у списку.
     * @return
    */
	public int size() {	    return len;    }
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
    public ArrayTaskList incoming1(int from, int to) {
        AbstractTaskList resList = new ArrayTaskList();
        if (false) {
            for (int i = 0; i < len; i++) {
                if (isIncoming(getTask(i), from, to, 2)) {
                    resList.add(getTask(i));
                }
            }
        } else {
            Stream<Task> stream = this.getStream();
            stream.filter(t->isIncoming(t, from, to, 2)).forEach( t->resList.add(t) );
        }

        /*
        Class<?> enclosingClass = getClass().getEnclosingClass();
        if (enclosingClass != null) {
            System.out.println(enclosingClass.getName());
        } else {
            System.out.println(getClass().getName());
        }
        */
         return (ArrayTaskList) resList;
    }
    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;

        ArrayTaskList that = (ArrayTaskList) o;
        if (len == 0 && that.len == 0) return true;

        return Arrays.equals(aTask, that.aTask);
    }
    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(aTask);
    }
    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList tmpTaskList =  (ArrayTaskList) super.clone();
        tmpTaskList.aTask = Arrays.copyOf(this.aTask, this.len);
        tmpTaskList.len = this.len;
        int i = 0;
        for (Task tmp: this.aTask) {
            Task tmpClone = tmp.clone();
            tmpTaskList.aTask[i++] = tmpClone;
        }
        /*
        for (int i = 0; i < len; i++) {
            Task tmp = getTask(i);
            Task tmpClone = tmp.clone();
            tmpTaskList.aTask[i] = tmpClone;
        } */
        return tmpTaskList;
    }



    public ArrayTaskList cloneClearArray() throws CloneNotSupportedException {
        ArrayTaskList tmpTaskList =  (ArrayTaskList) super.clone();
        tmpTaskList.aTask = null;
        tmpTaskList.len = 0;
        for (int i = 0; i < len; i++) {
            Task tmp = getTask(i);
            Task tmpClone = tmp.clone();
            tmpTaskList.add(tmpClone);
        }
        return tmpTaskList;
    }

    public ArrayTaskList cloneStream() {
        try {
            return cloneArrayTaskList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayTaskList cloneArrayTaskList() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);
        // задачу  объекта в поток байтов
        ous.writeObject(this);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        //эксгумация потока в Задачу
        //Task cloneTask = (Task) ois.readObject();
        return (ArrayTaskList) ois.readObject();
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

    /**
     * https://riptutorial.com/ru/java/example/686/%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-%D1%81%D0%BE%D0%B1%D1%81%D1%82%D0%B2%D0%B5%D0%BD%D0%BD%D0%BE%D0%B3%D0%BE-iterable-
     * http://qaru.site/questions/77139/can-we-write-our-own-iterator-in-java
     * @return
     */
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int curInd = 0; // фокус просмотра
            private boolean lCallNext = false; // флаг блокировки вызова Удаления без высова метода След

            @Override //Функция hasNext() проверяет, находится ли итератор в конце
            public boolean hasNext() {
                // э-ты еще есть пока curInd < длины массива и элемент не пустой
                return curInd < len && aTask[curInd] != null;
            }

            @Override
            public Task next() {
                lCallNext = true;
                return aTask[curInd++]; // вывод текущего и перенос на следующий фокуса просмора
            }

            @Override
            public void remove() {
                //"Виклик Iterator.remove без next повинен призводити до помилки"
                if (!lCallNext) throw new IllegalStateException();
                // удалять нечего
                if (! hasNext())   throw new NoSuchElementException();
                // возврат фокуса просмотра назад
                curInd--; // DEL текущий
                ADelAndASize(curInd);
            }
        };
    }

    @Override
    public  Stream<Task> getStream( ) {
        return Arrays.stream(aTask);
    }
    private void ADelAndASize(int index4Del) {
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
    }
}
/*

 } else { // foreach
            for (Task elem : aTask) {
                if (isIncoming(elem, from, to, 2)) {
                    resList.add(elem);
                }
            }
        }

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
