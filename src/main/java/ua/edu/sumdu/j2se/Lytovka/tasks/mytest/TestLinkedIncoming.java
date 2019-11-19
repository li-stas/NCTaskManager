package ua.edu.sumdu.j2se.lytovka.tasks.mytest;

import ua.edu.sumdu.j2se.lytovka.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.LinkedTaskList;
import ua.edu.sumdu.j2se.lytovka.tasks.Task;

public class TestLinkedIncoming {
    public static void main(String[] args) throws CloneNotSupportedException {
        LinkedTaskList ts = new LinkedTaskList();

        if (true) {
            ts.add(new Task("Simple IN", 55, true));
            ts.add(new Task("Simple OUT", 10, true));
            ts.add(new Task("Inactive OUT", 0, 1000, 1, false));
            ts.add(new Task("Simple bound OUT", 50, true));
            ts.add(new Task("Simple bound IN", 60, true));
        }
        ts.add(new Task("Repeat inside IN", 51, 58, 2, true));
        ts.add(new Task("Repeat outside IN", 0, 100, 5, true));
        ts.add(new Task("Repeat outside OUT", 0, 100, 22, true));
        ts.add(new Task("Repeat left OUT", 0, 40, 1, true));
        ts.add(new Task("Repeat right OUT", 65, 100, 1, true));
        ts.add(new Task("Repeat left intersect IN 1", 0, 55, 13, true));
        ts.add(new Task("Repeat left intersect IN 2", 0, 60, 30, true));
        ts.add(new Task("Repeat left intersect OUT", 0, 55, 22, true));
        ts.add(new Task("Repeat right intersect IN", 55, 100, 20, true));


        //System.out.println(ts);
        LinkedTaskList res1 = (LinkedTaskList) ts.incoming(50, 60);
        System.out.println(res1);
        System.out.println("res1.size()="+res1.size());

    }
}
