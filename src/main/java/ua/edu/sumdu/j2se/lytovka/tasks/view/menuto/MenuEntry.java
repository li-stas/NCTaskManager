package ua.edu.sumdu.j2se.lytovka.tasks.view.menuto;

public abstract class MenuEntry {

    private String title;
    private boolean lExit;

    public MenuEntry(String title) {
        this.title = title;
    }
    public MenuEntry(String title, boolean lExit) {
        this.title = title;
        this.lExit = lExit;
    }

    public String getTitle() {
        return title;
    }

    public boolean islExit() {
        return lExit;
    }

    public abstract void run();
}
