package ua.edu.sumdu.j2se.lytovka.tasks.controller.creat_list_exe_methods;

import java.util.ArrayList;
import java.util.List;

/**
 * контейнер, который будет в хранить все медоды обработки меню
 */
public class CtrlListRun {
    private List<RunEntry> entries = new ArrayList();

    public CtrlListRun() {
    }

    public void addEntry(RunEntry oRunEntry) {
        entries.add(oRunEntry);
    }

    public List getEntries() {
        return entries;
    }
}
