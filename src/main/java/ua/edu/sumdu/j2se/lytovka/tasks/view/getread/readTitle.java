package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

import java.util.Scanner;

public class readTitle {
    /**
     * ввод названия задания
     * @return
     */
    public String getread() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Название задания: ");
        String title = "";
        while (title.isEmpty()) {
            title = scan.nextLine();
        }
        return title;
    }
}
