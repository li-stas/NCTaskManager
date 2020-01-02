package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

public class readIsTaskRepit {
    /**
     * ввод Повторяемости задания
     * @return
     */
    public int getread() {
        System.out.print("Задание Повторяется? ");
        return new readYesNo().getnRet();
    }
}
