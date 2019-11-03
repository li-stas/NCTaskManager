package ua.edu.sumdu.j2se.Lytovka.tasks;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList implements Serializable {
    private LinkedTaskListNode fistNode; //  = new LinkedTaskListNode();
    private int len; // = 0;
    private LinkedTaskListNode lastNode; // = null;
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
        // поиск задачи
        String cTitle = task.getTitle();
        int index4Del = -1;

        LinkedTaskListNode curNode = fistNode;
        LinkedTaskListNode delNode;
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
            if (index4Del == 1) { // первый узел
                fistNode = fistNode.getNext();
                len--;
            } else if (false && curNode.getNext() == null) { // последний узел
                // предыдущая
                curNode = fistNode;
                for (int j = 2; j <= len - 1; j++) {
                    curNode = curNode.getNext();
                }
                // сделаем последней
                curNode.setNext(null);
                lastNode = curNode;
                len--;
            } else  {
                delNode = curNode; // запомним
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
        LinkedTaskList resList = new LinkedTaskList();
        int nType = 2;
        if (nType == 2) {
            for (int i = 0; i < len; i++) {
                if (isIncoming(getTask(i), from, to, nType)) {
                    resList.add(getTask(i));
                }
            }
        }
        if (nType == 1) {
            LinkedTaskListNode curNode = fistNode;
            while (true) {
                Task elem = curNode.getData();
                if (isIncoming(elem,  from,  to, nType)) {
                    resList.add(elem);
                }
                curNode = curNode.getNext();
                if (curNode == null) {
                    break;
                }
            }
        }
        return resList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;

        LinkedTaskList that = (LinkedTaskList) o;
        if (len != that.len) return false;
        if (len == 0 && that.len == 0 ) return true;

        // преведем в ArrayTaskList
        ArrayTaskList ArrayTaskListThis = LinkedTaskList2ArrayTaskList(this);
        ArrayTaskList ArrayTaskListThat = LinkedTaskList2ArrayTaskList(that);
        return Arrays.equals(ArrayTaskListThis.get_aTask(), ArrayTaskListThat.get_aTask());
    }
    @Override
    public int hashCode() {
        return Arrays.hashCode(LinkedTaskList2ArrayTaskList(this).get_aTask());
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

    //public LinkedTaskList cloneStream() {
    public LinkedTaskList clone() {
        try {
            return cloneLinkedTaskList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private LinkedTaskList cloneLinkedTaskList() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);
        // задачу  объекта в поток байтов
        ous.writeObject(this);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        //эксгумация потока в Задачу
        //Task cloneTask = (Task) ois.readObject();
        return (LinkedTaskList) ois.readObject();
    }


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
