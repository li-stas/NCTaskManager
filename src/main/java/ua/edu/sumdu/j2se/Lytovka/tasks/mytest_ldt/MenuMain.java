package ua.edu.sumdu.j2se.lytovka.tasks.mytest_ldt;

import java.util.Arrays;
import java.util.Scanner;

public class MenuMain {

    static void processChoice() {
        String[] aElem = new String[]{
                (char)27 + "[30m"+"1.	Cтворювати нову задачу",
                (char)27 + "[30m"+"2.	Змініти параметри існуючих задач",
                (char)27 + "[30m"+"3.	Видалити задачі",
                (char)27 + "[30m"+"4.	Переглянути інформацію про існуючі задачі",
                (char)27 + "[30m"+"5.	Переглянути календар запланованих подій на деякий проміжок часу",
                (char)27 + "[30m"+"0.	Завершити",
                "",
                (char)27 + "[37m"+"Write action (1 - 5, 0 - exit): "
        };
        for (String cElem:aElem) {
            System.out.printf("%-30s%n",cElem);
        }
    }

    static int nChoice(Scanner scan) {
        return scan.nextInt();
    }
}
