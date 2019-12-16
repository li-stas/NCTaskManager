package ua.edu.sumdu.j2se.lytovka.tasks.controller;

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
