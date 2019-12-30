package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.readYesNo;

public class readDoSaveTask {
    public int getnRet() {
        System.out.print("Задание записать? ");
        return new readYesNo().getnRet();
    }
}
