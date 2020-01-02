package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

public class readIsTaskActive {
    /**
     * ввод Активности задания
     * @return
     */
    public int getread() {
        System.out.print("Задание Активно? ");
        return new readYesNo().getnRet();
    }
}
