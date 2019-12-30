package ua.edu.sumdu.j2se.lytovka.tasks.view.screens;

import static ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView.doSayMess;

public class doSrcWarningTasks {
    public void Screen(String cMess, int nIntervalChk_SS) {
        doSayMess("\n");
        doSayMess((char) 27 + "[34m"
                + "Чз " + String.format("%4.1f", (float) nIntervalChk_SS/60)
                + " мин наступает вркмя выполнеия задания : "
                + cMess
                + (char)27 + "[37m"
                + "\n");
        doSayMess("\n");
    }
}
