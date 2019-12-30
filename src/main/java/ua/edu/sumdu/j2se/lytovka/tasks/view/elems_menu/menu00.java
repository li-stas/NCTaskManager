package ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.Menu;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.MenuEntry;

public class menu00 {
    final Logger log = Logger.getLogger(TasksView.class.getName());
    public int getnRet() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("1 - Редактировать", true) {public void run( ) {} });
        menu.addEntry(new MenuEntry("2 - Добавить", true) { public void run() {}  });
        menu.addEntry(new MenuEntry("3 - Удалить задание", true) {public void run() {} });
        menu.addEntry(new MenuEntry("4 - Календарь на период", true) { public void run() {} });
        System.out.print("Что хотите сделать заданиями? ");
        log.info("run menu00()");
        nRet = menu.run();
        return nRet;
    }

}
