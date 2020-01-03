package ua.edu.sumdu.j2se.lytovka.tasks.model;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList { //implements Iterable  {
    /**
     *  пустой конструктр.
     */
    public AbstractTaskList() { }
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
    public abstract Stream<Task> getStream();
    /**
     * знаходити, які саме задачі будуть виконані хоча б раз у деякому проміжку
     * @param from
     * @param to
     * @return
     */
    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        AbstractTaskList resList;

        if (this instanceof LinkedTaskList) {
            resList = new LinkedTaskList();
        } else {
            resList = new ArrayTaskList();
        }
        this.getStream().filter(t->isIncoming(t, from, to )).forEach( t->resList.add(t) );
        return resList;
    }
    /**
     *  задача буде виконана  деякому проміжку?
     * @param from
     * @param to
     * @return
     */

    public boolean isIncoming(Task elem, LocalDateTime from, LocalDateTime to) {
        LocalDateTime toTime = elem.nextTimeAfter(from);

        return elem.isActive() && toTime != null
                && (toTime.compareTo(to) <= 0);
    }
}

