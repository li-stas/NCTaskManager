package ua.edu.sumdu.j2se.lytovka.tasks.view.lib;

public class intervalHHMM {
    /**
     * форматирование интервала
     * @param time
     * @return
     */
    public String get_intervalHHMM(long time) {
        return String.format("%02d:%02d:%02d", time / 3600, time / 60 % 60, time % 60);
    }
}
