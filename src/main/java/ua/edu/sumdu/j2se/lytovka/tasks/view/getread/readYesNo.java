package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

import ua.edu.sumdu.j2se.lytovka.tasks.view.lib.menuto.Menu;
import ua.edu.sumdu.j2se.lytovka.tasks.view.lib.menuto.MenuEntry;
/**
 *   GET|READ меню запроса Да/Нет/Отмена
 * @return номер выбранно п. меню
 */
public class readYesNo {
    /**
     *   GET|READ меню запроса Да/Нет/Отмена
     * @return номер выбранно п. меню
     */
    public int getnRet() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("1 - Да", true) {public void run() {}  });
        menu.addEntry(new MenuEntry("2 - Нет", true) {public void run() {} });
        nRet = menu.run();
        return nRet;
    }
}
