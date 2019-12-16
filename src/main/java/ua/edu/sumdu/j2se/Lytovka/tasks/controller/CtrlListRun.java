package ua.edu.sumdu.j2se.lytovka.tasks.controller;

import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.MenuEntry;

import java.util.ArrayList;
import java.util.List;

public class CtrlListRun {
    private List entries = new ArrayList();

    public CtrlListRun() {
    }

    public void addEntry(RunEntry oRunEntry) {
        entries.add(oRunEntry) ;
    }

    public List getEntries() {
        return entries;
    }
}
