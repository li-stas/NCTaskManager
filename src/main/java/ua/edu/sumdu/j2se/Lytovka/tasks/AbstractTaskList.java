package ua.edu.sumdu.j2se.Lytovka.tasks;

public abstract class AbstractTaskList  {
    private Task[] aTask = null;
    private int len = 0;
    /**
     *  пустой конструктр.
     */
    public AbstractTaskList() {
        this.aTask = null;
        this.len = 0;
    }
    /**
     * метод, що додає до списку задачу.
     * @param task
     * @throws IllegalArgumentException
     */
    public abstract void add(Task task) ;
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
    /**
     * знаходити, які саме задачі будуть виконані хоча б раз у деякому проміжку
     * @param from
     * @param to
     * @return
     */
    public abstract AbstractTaskList incoming(int from, int to);

}
