package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

public class readDoSaveTask {
    /**
     * запрос на запись
     * @return
     */
    public int getnread() {
        System.out.print("Задание записать? ");
        return new readYesNo().getnRet();
    }
}
