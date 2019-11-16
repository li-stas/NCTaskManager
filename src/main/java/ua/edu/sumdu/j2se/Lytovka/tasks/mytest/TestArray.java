package ua.edu.sumdu.j2se.Lytovka.tasks.mytest;

import ua.edu.sumdu.j2se.Lytovka.tasks.ArrayTaskList;
import ua.edu.sumdu.j2se.Lytovka.tasks.Task;

import java.util.stream.Stream;

public class TestArray {
	//private static final ListTypes.types ARRAY = ;

	public static void main(String[] args) throws CloneNotSupportedException {
		/*
		//TaskListFactory.createTaskList(ARRAY);
		Task task2 = new Task("some", 10, 100, 20);
		task2.setActive(true);
		int next2 = task2.nextTimeAfter(85);
		System.out.println(next2);
		System.exit(999);
 		*/
		ArrayTaskList tasksList = new ArrayTaskList();

		if (false) {
			boolean lEquals = tasksList.equals(null);
			System.out.println("lEquals="+lEquals);
			System.exit(999);
		}


		Task task2 = new Task("B", 1);
		Task task3 = new Task("C", 2);
		tasksList.add(task2);
		tasksList.add(task3);

 		Task task1 = new Task("A", 0);
		tasksList.add(task1);

		// клонирование
		ArrayTaskList tskClone =  tasksList.clone();
		System.out.println("tasksList->"+tasksList);
		System.out.println("tskClone->"+tskClone);
		System.out.println("");
		tskClone.getTask(0).setTitle("A1");
		//tskClone.getTask(1).setTitle("B1");
		//tskClone.getTask(2).setTitle("C1");
		System.out.println("tasksList="+tasksList);
		System.out.println("tskClone="+tskClone);

		Stream<Task> stream = tskClone.getStream();
		System.out.println("Stream<Task> stream");
		stream.forEach(System.out::println);

		System.exit(999);



		int tasksListSize = tasksList.size();
		System.out.println("TasksList.size()="+tasksListSize);


		tasksList.add(new Task("D", 2));
		System.out.println("TasksList.size()="+tasksList.size());

		if (false) {
			System.out.println(tasksList);
			ArrayTaskList res = tasksList.incoming(0, 1000);
			System.out.println("res.size()=" + res.size());

		}
	}
	public static void main3(String[] args) {
		ArrayTaskList tasksList = new ArrayTaskList();
		Task task1 = new Task("A", 10);
		Task task2 = new Task("B", 20); //, 100, 20);
		Task task3 = new Task("C", 30);
		tasksList.add(task1);		//System.out.println(tasksList);
		tasksList.add(task2);		//System.out.println(tasksList +" "+ tasksList.size());
		tasksList.add(task3);
		System.out.println(tasksList);

		System.out.println(tasksList.remove(task3));
		System.out.println(tasksList);


	}

	public static void main2(String[] args) {
		Task task;
		if (false) { // одно разовая задача
			task = new Task("some", 10);
			task.setActive(true);
			System.out.printf("nextTimeAfter 0 %d %d\n", 10, task.nextTimeAfter(0));
			System.out.printf("nextTimeAfter 9 %d %d\n", 10, task.nextTimeAfter(9));
			System.out.printf("nextTimeAfter 10 %d %d\n", -1, task.nextTimeAfter(10));
			System.out.printf("nextTimeAfter 100 %d %d\n", -1, task.nextTimeAfter(100));
		}
		if (true) {
			task = new Task("some", 10, 100, 20);
			task.setActive(true);
			System.out.printf("nextTimeAfter 0 %d %d\n", 10, task.nextTimeAfter(0));
			System.out.printf("nextTimeAfter 9 %d %d\n", 10, task.nextTimeAfter(9));

			System.out.printf("nextTimeAfter 30 %d %d\n", 50, task.nextTimeAfter(30));
			System.out.printf("nextTimeAfter 40 %d %d\n", 50, task.nextTimeAfter(40));
			System.out.printf("nextTimeAfter 10 %d %d\n", 30, task.nextTimeAfter(10));

			System.out.printf("nextTimeAfter 95 %d %d\n", -1, task.nextTimeAfter(95));
			System.out.printf("nextTimeAfter 100 %d %d\n", -1, task.nextTimeAfter(100));
		}
	}
	public static void main1(String[] args) {
		Task task;
		if (false) {
			task = new Task("Task title", 42);
			System.out.println(task);
			System.out.println(task.getTitle()); //"Task title",
			System.out.println(task.isRepeated()); //f
			System.out.println(task.isActive()); //f
			System.out.println(task.getTime()); //42
			System.out.println(task.getStartTime()); //42
			System.out.println(task.getEndTime()); //42
			System.out.println(task.getRepeatInterval());// 0
		}

		task = new Task("Task title", 5, 25, 3);
		System.out.println(task);
		if (false) {
			System.out.printf("task.getTitle()=%s\n", task.getTitle()); //"Task title",
			System.out.printf("task.isRepeated()=%s\n", task.isRepeated() ? ".t." : ".f."); //f
			System.out.printf("task.isActive()=%s\n", task.isActive() ? ".t." : ".f."); //f
			System.out.printf("task.getStartTime()=%d\n", task.getStartTime());
			System.out.printf("task.getEndTime()=%d\n", task.getEndTime());
			System.out.printf("task.getRepeatInterval()=%d\n", task.getRepeatInterval());// 0
		}
		task.setTime(42);
		if (false) {
			System.out.println(task);
			System.out.printf("task.isRepeated()=%s\n", task.isRepeated() ? ".t." : ".f."); //f
			System.out.printf("task.task.getTime()=%s\n", task.getTime());
			System.out.printf("task.getStartTime()=%d\n", task.getStartTime());
			System.out.printf("task.getEndTime()=%d\n", task.getEndTime());
			System.out.printf("task.getRepeatInterval()=%d\n", task.getRepeatInterval());// 0
		}
		task.setTime(5, 25, 3);
		System.out.println(task);
		System.out.printf("task.getStartTime()=%d\n", task.getStartTime());
		System.out.printf("task.getEndTime()=%d\n", task.getEndTime());
		System.out.printf("task.getRepeatInterval()=%d\n", task.getRepeatInterval());// 0
		System.out.printf("task.task.getTime()=%s\n",task.getTime());

	}
}
