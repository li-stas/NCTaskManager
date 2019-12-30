package ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu;

import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.Menu;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.MenuEntry;

public class readYesNo {
    public int getnRet() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("1 - Да", true) {public void run() {}  });
        menu.addEntry(new MenuEntry("2 - Нет", true) {public void run() {} });
        nRet = menu.run();
        return nRet;
    }
}
