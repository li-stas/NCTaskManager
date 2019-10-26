package ua.edu.sumdu.j2se.Lytovka.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private Task [] aTask = null;
    private int len = 0;

    public ArrayTaskList() {
        this.aTask = null;
        this.len = 0;
    }
    /**
     * метод, що додає до списку вказану задачу.
     */
    public 	void add(Task task) {
        if (len == 0) {
            this.aTask = new Task[]{task};
            len++;
        } else {
            // AADD
            Task [] aTmp = new Task[len + 1]; // создаем увеличенный размер
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
     */
    public boolean remove(Task task) {
        if ( len == 0  ) {
            return false; // ??
        }
        String cTitle = task.getTitle();
        int index4Del = -1;
        for (int i = 0; i < len; i++) {
           if (cTitle.startsWith(aTask[i].getTitle())) {
               index4Del = i;
           }
        }
        // удаление выбранного и смещение елементов
        if (index4Del >= 0) {

            // ADEL(,index4Del)
            // удаление выбранного и смещение елементов
            for (int i = index4Del; i < len-1; i++) {
                aTask[i] = aTask[i+1];
            }
            aTask[len-1] = null;
            /// end ADEL
            // ASIZE()
            Task [] aTmp = new Task[len - 1]; // создаем уменьшенный размер
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
     */
	public int size() {
	    return len;
    };
    /**
     *  – метод, що повертає задачу, яка
     *  знаходиться на вказаному місці у списку,
     *  перша задача має індекс 0.
     */
    public Task getTask(int index) {
        if ( len == 0 || index > len - 1 ) {
            return null; // ??
        }
        return aTask[index];
    };

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "aTask=" + Arrays.toString(aTask) +
                ", len=" + len +
                '}';
    }
}
