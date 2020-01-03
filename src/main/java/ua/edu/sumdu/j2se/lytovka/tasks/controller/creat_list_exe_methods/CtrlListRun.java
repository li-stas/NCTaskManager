package ua.edu.sumdu.j2se.lytovka.tasks.controller.creat_list_exe_methods;

import java.util.ArrayList;

/**
 * контейнер, который будет в хранить все медоды обработки меню
 */
public class CtrlListRun {
    private ArrayList<RunEntry> entries = new ArrayList<>();

    public CtrlListRun() {
    }

    public void addEntry(RunEntry oRunEntry) {
        entries.add(oRunEntry);
    }

    public ArrayList<RunEntry> getEntries() {
        return entries;
    }
}
