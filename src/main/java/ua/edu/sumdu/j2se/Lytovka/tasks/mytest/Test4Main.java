package ua.edu.sumdu.j2se.lytovka.tasks.mytest;

import ua.edu.sumdu.j2se.lytovka.tasks.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.TaskListFactory;

import java.util.ArrayList;
import java.util.Arrays;

import static ua.edu.sumdu.j2se.lytovka.tasks.ListTypes.types.ARRAY;

public class Test4Main {
	public static void main(String[] args) {
		System.out.println("res.size()=");
		TaskListFactory.createTaskList(ARRAY);

		Task t ;//= new Task();


		ArrayList<String> a1 = new ArrayList<>(Arrays.asList(new String[]{"A1", "A2"}));
		System.out.println("a1" + a1);
		ArrayList b1 = (ArrayList) a1.clone();
		System.out.println("b1" + b1);
		b1.set(0, "B1");
		b1.set(1, "B2");
		System.out.println("b1" + b1);


	}
}
