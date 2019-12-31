package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

public class readIsTaskActive {
    public int getnRet() {
        System.out.print("Задание Активно? ");
        return new readYesNo().getnRet();
    }
}
