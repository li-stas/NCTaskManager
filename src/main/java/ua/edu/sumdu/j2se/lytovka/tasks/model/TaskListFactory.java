package ua.edu.sumdu.j2se.lytovka.tasks.model;


public class TaskListFactory {
    /**
     * TaskListFactory.createTaskList(ARRAY_LIST)
     * https://javarush.ru/groups/posts/modifikator-static-java
     * отсутствует необходимость каждый раз создавать новый объект для доступа к таким методам. Статический метод можно
     * вызвать, используя тип класса, в котором эти методы описаны. Именно поэтому, подобные методы как нельзя лучше
     * подходят в качестве методов-фабрик (factory),
     * @param type
     * @return
     */
   public static AbstractTaskList createTaskList(ListTypes.types type)  {
        switch (type) {
            case ARRAY:
                return new ArrayTaskList();
            case LINKED:
               return new LinkedTaskList();
            default:
                return null;
        }
   }
}
