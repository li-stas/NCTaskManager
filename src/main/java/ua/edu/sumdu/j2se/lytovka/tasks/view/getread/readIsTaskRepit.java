package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.readYesNo;

public class readIsTaskRepit {
    public int getnRet() {
        System.out.print("Задание Повторяется? ");
        return new readYesNo().getnRet();
    }
}
