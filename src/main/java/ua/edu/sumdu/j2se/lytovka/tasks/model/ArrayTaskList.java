package ua.edu.sumdu.j2se.lytovka.tasks.model;

import java.io.*;
import java.time.LocalDateTime;
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
           if (cTitle.equals(aTask[i].getTitle())) {
               index4Del = i;
               break;
           }
        }
        // удаление выбранного и смещение елементов
        if (index4Del >= 0) {
            ADelAndASize(index4Del);
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
    public ArrayTaskList incoming1(LocalDateTime from, LocalDateTime to) {
        ArrayTaskList resList = new ArrayTaskList();
        Stream<Task> stream = this.getStream();
        stream.filter(t -> isIncoming(t, from, to)).forEach(t -> resList.add(t));
        return resList;
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
        return tmpTaskList;
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
    public  Stream<Task> getStream() {
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

    public Task CreateTaskOne(String title, LocalDateTime time) {
        Task tmp = new Task(title,time);
        tmp.setActive(true);
        return tmp;
    }
    public Task CreateTaskRepite(String title, LocalDateTime start, LocalDateTime end, int interval) {
        Task tmp = new Task(title, start, end, interval);
        tmp.setActive(true);
        return tmp;
    }
}
