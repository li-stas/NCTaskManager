package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;
import ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu.readYesNo;

public class readDoRemoveTask {
    public int getnRet(){
        System.out.print("Задание удалить? ");
        return new readYesNo().getnRet();
    }
}
