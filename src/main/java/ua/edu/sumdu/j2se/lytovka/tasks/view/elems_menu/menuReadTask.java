package ua.edu.sumdu.j2se.lytovka.tasks.view.elems_menu;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;
import ua.edu.sumdu.j2se.lytovka.tasks.view.intervalHHMM;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.Menu;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.MenuEntry;

public class menuReadTask {
    final Logger log = Logger.getLogger(TasksView.class.getName());
    private Task t;

    public menuReadTask(Task t) {
        this.t = t;
    }

    public int getnRet() {
        int nRet;
        Menu menu = new Menu(1);
        menu.addEntry(new MenuEntry(String.format("%-25s", "1 - Название") + ":" + t.getTitle(),
                true) { public void run() {} });
        menu.addEntry(new MenuEntry(String.format("%-25s", "2 - Актинвное") + ":" + (t.isActive() ? "Да" : "Нет"),
                true) {public void run() {} });
        if (t.isRepeated()) {
            menu.addEntry(new MenuEntry(String.format("%-25s", "3 - Время начала") + ":" + t.getStartTime(),
                    true) {public void run() {} });
            menu.addEntry(new MenuEntry(String.format("%-25s", "4 - Время окончания") + ":" + t.getEndTime(),
                    true) {public void run() {} });
            menu.addEntry(new MenuEntry(String.format("%-25s", "5 - Интервал повторения") + ":" + intervalHHMM(t.getRepeatInterval()),
                    true) { public void run() {}   });
        } else {
            menu.addEntry(new MenuEntry(String.format("%-25s", "3 - Время начала") + ":" + t.getStartTime(),
                    true) { public void run() {} });
        }
        log.info("run menuReadTask");
        nRet = menu.run();
        return nRet;
    }
    /**
     *  Преобразование интервала выраженного  сек в
     *    интервал в Часах и Минутах
     * @param time
     * @return
     */
    private String intervalHHMM(long time) {
        return new intervalHHMM().get_intervalHHMM(time);
    }
}
