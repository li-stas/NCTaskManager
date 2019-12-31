package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

public class readIsTaskRepit {
    public int getnRet() {
        System.out.print("Задание Повторяется? ");
        return new readYesNo().getnRet();
    }
}
