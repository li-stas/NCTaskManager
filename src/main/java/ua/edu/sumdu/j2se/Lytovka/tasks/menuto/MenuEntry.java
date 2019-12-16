package ua.edu.sumdu.j2se.lytovka.tasks.view.menuto;

public abstract class MenuEntry {
    private String title;

    public MenuEntry(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract void run();
}
