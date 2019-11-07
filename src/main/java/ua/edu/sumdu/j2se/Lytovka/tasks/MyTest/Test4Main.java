package ua.edu.sumdu.j2se.Lytovka.tasks.MyTest;

import ua.edu.sumdu.j2se.Lytovka.tasks.Task;

import javax.management.openmbean.ArrayType;
import java.util.ArrayList;
import java.util.Arrays;

public class Test4Main {
	public static void main(String[] args) {
		System.out.println("res.size()=");

		//Task t = new Task();


		ArrayList a1 = new ArrayList(Arrays.asList(new String[]{"A1", "A2"}));
		System.out.println("a1" + a1);
		ArrayList b1 = (ArrayList) a1.clone();
		System.out.println("b1" + b1);
		b1.set(0, "B1");
		b1.set(1, "B2");
		System.out.println("b1" + b1);


	}
}
