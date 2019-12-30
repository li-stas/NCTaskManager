package ua.edu.sumdu.j2se.lytovka.tasks.controller.creat_list_exe_methods;

/**
 *  поле curNumEntry, а также абстрактный метод run.
 *  описывается, что должно произойти при выборе этого пункта меню
 */
public abstract class RunEntry {
    private int  curNumEntry;

    public RunEntry(int i) {
         this.curNumEntry = i;
    }

    public int getCurNumEntry() {
        return curNumEntry;
    }

    public abstract void run();
}
