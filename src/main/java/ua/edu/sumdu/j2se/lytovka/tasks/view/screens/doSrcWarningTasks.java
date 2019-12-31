package ua.edu.sumdu.j2se.lytovka.tasks.view.screens;

public class doSrcWarningTasks {
    public void Screen(String cMess, int nIntervalChk_SS) {
        System.out.print("\n");
        System.out.print((char) 27 + "[34m"
                + "Чз " + String.format("%4.1f", (float) nIntervalChk_SS/60)
                + " мин наступает вркмя выполнеия задания : "
                + cMess
                + (char)27 + "[37m"
                + "\n");
        System.out.print("\n");
    }
}
