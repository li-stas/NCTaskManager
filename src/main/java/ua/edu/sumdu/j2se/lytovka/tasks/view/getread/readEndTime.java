package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

import ua.edu.sumdu.j2se.lytovka.tasks.view.TasksView;

import java.time.LocalDateTime;

public class readEndTime {
    /**
     * ввод даты/времени Окончания
     * @param d4Valid
     * @return
     */
    public LocalDateTime getread(LocalDateTime d4Valid) {
        System.out.println("Время оконончания: ");
        return TasksView.readLclDtTm(d4Valid);
    }
}
