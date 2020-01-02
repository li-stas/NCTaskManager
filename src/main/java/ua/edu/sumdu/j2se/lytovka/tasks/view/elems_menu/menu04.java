package ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu;

import ua.edu.sumdu.j2se.lytovka.tasks.view.lib.menuto.Menu;
import ua.edu.sumdu.j2se.lytovka.tasks.view.lib.menuto.MenuEntry;

public class menu04 {
    /**
     * мендю запроса формирования календаря
     * @return
     */
    public int getnRet() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("1 - На 24 часа", true) { public void run() {} });
        menu.addEntry(new MenuEntry("2 - На неделю", true) { public void run() {} });
        menu.addEntry(new MenuEntry("3 - На месяц", true) { public void run() {} });
        menu.addEntry(new MenuEntry("4 - На год", true) {  public void run() {} });
        System.out.print("На какой период построить календарь? ");
        nRet = menu.run();
        return nRet;
    }
}
