package ua.edu.sumdu.j2se.lytovka.tasks.model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

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
            if (cTitle.equals(curNode.getData().getTitle())) {
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
     * https://habr.com/ru/company/luxoft/blog/270383/
     * https://www.geeksforgeeks.org/stream-builder-add-method-in-java/
     *
     * @return
     */
    @Override
    public Stream<Task> getStream( ) {
        Stream.Builder<Task> builder = Stream.builder();
        for (Iterator<Task> it = this.iterator(); it.hasNext();) {
            Task t = it.next();
            builder.add(t);
        }
        return (Stream<Task>) builder.build();
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
        StringBuilder strBuffer = new StringBuilder(cOut);
        if (len != 0) {
            LinkedTaskListNode curNode = fistNode;
            int i = 1;
            while (true) {
                strBuffer.append(curNode.getData()).append(" node=").append(i);
                //cOut +=  curNode.getData() + " node=" + i;
                curNode = curNode.getNext();
                if (curNode == null) {
                    break;
                }
                i++;
            }
        }
        cOut = strBuffer.toString();
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
        } catch (IOException | ClassNotFoundException e) {
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

        if (index4Del == 1) { // первый узел
            fistNode = fistNode.getNext();
            len--;
        }   else {
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
    static class LinkedTaskListNode implements Serializable {
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

