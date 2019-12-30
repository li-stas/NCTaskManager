package ua.edu.sumdu.j2se.lytovka.tasks.view.screens;
import static ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView.doSayMess;

public class doSrcMaxTasks {
    public void Screen() {
        doSayMess("\n");
        doSayMess((char) 27 + "[31m" + "К-во заданий ограничено 10" + (char)27 + "[37m" + "\n");
        doSayMess("\n");
    }
}
