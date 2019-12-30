package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

import java.util.Scanner;

public class readTitle {
    public String getTitle() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Название задания: ");
        String title = "";
        while (title.isEmpty()) {
            title = scan.nextLine();
        }
        return title;
    }
}
