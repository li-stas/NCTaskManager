package ua.edu.sumdu.j2se.Lytovka.tasks;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedTaskList extends AbstractTaskList implements Serializable, Iterable {
    private LinkedTaskListNode fistNode; //  = new LinkedTaskListNode();
    private LinkedTaskListNode lastNode; // = null;
    private int len; // = 0;
    /**
     *  пустой конструктр.
     */
    public LinkedTaskList() {
        fistNode = new LinkedTaskListNode();
        len = 0;
        lastNode = null;
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
            fistNode.setData(task);
            lastNode = fistNode;
            len++;
        } else {
            // AADD
            LinkedTaskListNode newNode = new LinkedTaskListNode(); // создали новый
            newNode.setData(task); // записали данные
            lastNode.setNext(newNode); // в последнию ноду записали ссылку новой
            lastNode = newNode;  // новую сделли последней
            len++;
        }

    }
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
        // поиск задачи
        String cTitle = task.getTitle();
        int index4Del = -1;

        LinkedTaskListNode curNode = fistNode;

        int i = 1;
        while (true) {
            if (cTitle.startsWith(curNode.getData().getTitle())) {
                index4Del = i;
                break;
            }
            curNode = curNode.getNext();
            if (curNode == null) {
                break;
            }
            i++;
        }
        // System.out.println("curNode.getNext()=" + curNode.getNext());
        // System.out.printf("index4Del %d, len %d \n",index4Del, len);
        // удаление выбранного и смещение елементов
        if (index4Del >= 0) {
            ADelAndASize(index4Del, curNode);
            return true;
        }
        return false;
    }
    /**
     *  метод, що повертає кількість задач у списку.
     * @return
     */
    public int size() {        return len;    }
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
        LinkedTaskListNode curNode = fistNode;
        for (int j = 2; j <= index + 1; j++) {
            curNode = curNode.getNext();
        }
        return curNode.getData();
    }
    /**
     * знаходити, які саме задачі будуть виконані хоча б раз у деякому проміжку
     * @param from
     * @param to
     * @return
     */
    public LinkedTaskList incoming(int from, int to) {
       AbstractTaskList resList = new LinkedTaskList();
            for (int i = 0; i < len; i++) {
                if (isIncoming(getTask(i), from, to, 2)) {
                    resList.add(getTask(i));
                }
            }
        return (LinkedTaskList) resList;
    }
     /**
     * toString.
     */
    @Override
    public String toString() {
        String cOut =  "LinkedTaskList{"
                + "fistNode=" + fistNode
                + ", len=" + len
                + ", lastNode=" + lastNode
                + '}';
        if (len != 0) {
            LinkedTaskListNode curNode = fistNode;
            int i = 1;
            while (true) {
                cOut +=  curNode.getData() + " node=" + i;
                curNode = curNode.getNext();
                if (curNode == null) {
                    break;
                }
                i++;
            }
        }
        return cOut;
    }
    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        boolean lEq = true;
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;

        LinkedTaskList that = (LinkedTaskList) o;
        if (len != that.len) return false;
        if (len == 0 && that.len == 0) return true;

        LinkedTaskListNode curNodeThat = that.fistNode;
        LinkedTaskListNode curNodeThis = this.fistNode;

        while (true) {
            // Task taskThat = curNodeThat.getData();            Task taskThis = curNodeThis.getData();
            if (!curNodeThat.getData().equals(curNodeThis.getData())) {
                lEq = false;
                break;
            }
            // переход на след. узел
            curNodeThis = curNodeThis.getNext();
            curNodeThat = curNodeThat.getNext();
            if (curNodeThis == null) {
                break;
            }
        }
        return lEq;
    }
    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        Task[] tmp = new Task[len];
        LinkedTaskListNode curNodeThis = fistNode;
        int i = 0;
        while (true) {
            tmp[i++] =  curNodeThis.data;
            if (curNodeThis == null) {
                break;
            }
        }
        return Arrays.hashCode(tmp);
    }
    /**
     * //public LinkedTaskList cloneStream()
     * https://habr.com/ru/post/246993/
     * https://javarush.ru/groups/posts/2022-serializacija-i-deserializacija-v-java
     * @return
     */
    public LinkedTaskList clone() {
        try {
            // создаем 2 потока для сериализации объекта и сохранения его в байты (файл)
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);
            // задачу  объекта в поток байтов
            ous.writeObject(this);
            ous.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            //эксгумация потока в Задачу
            return (LinkedTaskList) ois.readObject();    //return cloneLinkedTaskList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private LinkedTaskList cloneLinkedTaskList() throws IOException, ClassNotFoundException {
        // создаем 2 потока для сериализации объекта и сохранения его в байты (файл)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);
        // задачу  объекта в поток байтов
        ous.writeObject(this);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        //эксгумация потока в Задачу
        return (LinkedTaskList) ois.readObject();
    }
    //@Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int curInd = 1; // фокус просмотра
            private boolean lCallNext = false; // флаг блокировки вызова Удаления без высова метода След
            private LinkedTaskListNode curNodeI = fistNode;

            @Override
            public boolean hasNext() {
                return len != 0 && curInd <= len;
            }

            @Override
            public Task next() {
                lCallNext = true;
                if (curInd != 1) {
                    curNodeI = curNodeI.getNext();
                }
                curInd++;
                return curNodeI.getData();
            }

            @Override
            public void remove() {
                //"Виклик Iterator.remove без next повинен призводити до помилки"
                if (!lCallNext) throw new IllegalStateException();
                // удалять нечего
                if (! hasNext())   throw new NoSuchElementException();
                // возврат фокуса просмотра назад
                curInd--; // DEL текущий
                if (curInd == 1) {
                    fistNode = fistNode.getNext();
                    len--;

                    curNodeI = fistNode;
                } else {
                    LinkedTaskListNode curNode;

                    LinkedTaskListNode delNode = curNodeI; // запомним
                    // предыдущего узла  поиск
                    curNode = fistNode;
                    for (int j = 2; j <= curInd - 1; j++) {
                        curNode = curNode.getNext();
                    }
                    curNode.setNext(delNode.getNext());
                    if (delNode.getNext() == null) {
                        lastNode = curNode;
                    }
                    len--;

                    curNodeI = curNode;

                }
            }
        };
    }
    //@Override
    public Iterator<Task> iterator3() {
        return new Iterator<Task>() {
            private int curInd = 1; // фокус просмотра
            private boolean lCallNext = false; // флаг блокировки вызова Удаления без высова метода След

            @Override
            public boolean hasNext() {
                return len != 0 && curInd <= len;
            }

            @Override
            public Task next() {
                lCallNext = true;
                LinkedTaskListNode curNode = curNode(curInd, fistNode);
                curInd++;
                return curNode.getData();
            }

            @Override
            public void remove() {
                //"Виклик Iterator.remove без next повинен призводити до помилки"
                if (!lCallNext) throw new IllegalStateException();
                // удалять нечего
                if (! hasNext())   throw new NoSuchElementException();
                // возврат фокуса просмотра назад
                curInd--; // DEL текущий
                LinkedTaskListNode curNode = curNode(curInd, fistNode);
                System.out.println(curNode.getData() +" "+curInd);
                ADelAndASize(curInd, curNode);
            }
        };
    }
    private LinkedTaskListNode curNode(int curInd, LinkedTaskListNode curNode) {
        // адрес Ноды
        if (curInd > 1) {
            int i = 2;
            while (true) {
                curNode = curNode.getNext();
                if (curNode == null) {
                    break;
                }
                if (i == curInd) {
                    break;
                }
                i++;
            }
        }
        return curNode;
    }

    private void  ADelAndASize(int index4Del, LinkedTaskListNode curNode) {
        //LinkedTaskListNode curNode = fistNode;
        //LinkedTaskListNode delNode;
        if (index4Del == 1) { // первый узел
            fistNode = fistNode.getNext();
            len--;
        }  else if (false && curNode.getNext() == null) { // последний узел
            // предыдущая
            curNode = fistNode;
            for (int j = 2; j <= len - 1; j++) {
                curNode = curNode.getNext();
            }
            // сделаем последней
            curNode.setNext(null);
            lastNode = curNode;
            len--;
        } else {
            LinkedTaskListNode delNode = curNode; // запомним
            // предыдущего узла  поиск
            curNode = fistNode;
            for (int j = 2; j <= index4Del - 1; j++) {
                curNode = curNode.getNext();
            }
            curNode.setNext(delNode.getNext());
            if (delNode.getNext() == null) {
                lastNode = curNode;
            }
            len--;
        }
    }


    /**
     *
     */
    class LinkedTaskListNode implements Serializable {
        private Task data = null;
        private LinkedTaskListNode next = null;

        public LinkedTaskListNode() {
        }

        public Task getData() {
            return data;
        }

        public void setData(Task data) {
            this.data = data;
        }

        public LinkedTaskListNode getNext() {
            return next;
        }

        public void setNext(LinkedTaskListNode next) {
            this.next = next;
        }
    }




    /**
     * перевод из одного формата в другой
     * @param linkedTaskList
     * @return
     */
    private ArrayTaskList LinkedTaskList2ArrayTaskList(LinkedTaskList linkedTaskList) {
        LinkedTaskListNode curNode = fistNode;
        ArrayTaskList tmp = new ArrayTaskList();
        //int i = 1;
        while (true) {
            tmp.add(curNode.getData()); // добавим узел в ArrayTaskList
            // переход на след. узел
            curNode = curNode.getNext();
            if (curNode == null) {
                break;
            }
            //i++;
        }
        return tmp;
    }
}

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

/*} else if ( false && curNode.getNext() == null ) { // последний узел
                // предыдущая
                curNode = fistNode;
                for (int j = 2; j <= len - 1 ; j++) {
                    curNode = curNode.getNext();
                }
                // сделаем последней
                curNode.setNext(null);
                lastNode = curNode;
                len--; */

            /*
            // поиск последней
            LinkedTaskListNode curNode = fistNode;
            for (int j = 2; j <= len; j++) {
                curNode = curNode.getNext();
            }
            //System.out.println("curNode.getNext()=" + curNode.getNext());
            //System.out.println("curNode.getNext()=" + lastNode.getNext());
            lastNode = curNode;
*/
