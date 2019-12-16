package ua.edu.sumdu.j2se.lytovka.tasks.view.menuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu  {
    private List entries = new ArrayList();
    private boolean isExit = false;

    public Menu() {
    }
    public void addEntry(MenuEntry oMenuEntry) {
        entries.add(oMenuEntry) ;
    }

    public void run() {
        // Добавляем пункт меню Exit
        entries.add(new MenuEntry(entries.size() + 1  + " - Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
        // Бесконечный цикл, пока не нажали кнопку выход
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit) {
            printMenu();
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);

                if (choice > entries.size() || choice == 0) {
                  continue;
                }
                // Выбираем нажатый пункт меню и выполняем его код
                MenuEntry entry = (MenuEntry) entries.get(choice - 1);
                entry.run();

            } catch (IOException e ) {
                continue;
                //e.printStackTrace();
            }
            catch (NumberFormatException e) {
                continue;
            }
        }
    }

    private void printMenu() {
        for (int i = 0; i < entries.size() - 1 ; i++) {
            MenuEntry entry = (MenuEntry) entries.get(i);
           System.out.print(entry.getTitle()+", ");
        }
        MenuEntry entry = (MenuEntry) entries.get(entries.size() - 1);
        System.out.print(entry.getTitle()+": ");
    }


}
