package ua.edu.sumdu.j2se.lytovka.tasks.view;


import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.Menu;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.MenuEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TasksView {

    public int menu00() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("Что хотите сделать заданиями? 1 - Редактировать", true) {
            @Override
            public void run() {
                //System.out.println("test1 run");
            }
        });
        menu.addEntry(new MenuEntry("2 - Добавить", true) {
            @Override
            public void run() {
                //System.out.println("test2 run");
            }
        });

        menu.addEntry(new MenuEntry("3 - Информация о задании", true) {
            @Override
            public void run() {
                //System.out.println("test3 run");
            }
        });
        menu.addEntry(new MenuEntry("4 - Календарь на период", true) {
            @Override
            public void run() {
                //System.out.println("test4 run");
            }
        });
        nRet = menu.run();
        return nRet;

    }

    public void doSayMess(String cMess) {
        System.out.printf(cMess);
    }

    public void doSrcTask() {

    }

    public void doSrcEmptyTask() {
        doSayMess("\n");
        doSayMess((char) 27 + "[33m" + "Список заданий пустой\n");
        doSayMess("\n");
    }

    public String readTitle() {
        Scanner scan = new Scanner(System.in);
        doSayMess("Название задачи: ");
        String title = "";
        while (title.isEmpty()){
            title = scan.nextLine();
        }
        return title;
    }

    public int readDoSaveTask() {
        System.out.print("Задание записать? ");
        return readYesNo();
    }
    public int readIsTaskRepit() {
        System.out.print("Задание повторяется? ");
        return readYesNo();
    }

    private int readYesNo() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("1 - Да", true) {
            public void run() {
            }
        });
        menu.addEntry(new MenuEntry("2 - Нет", true) {
            public void run() {
            }
        });
        nRet = menu.run();
        return nRet;
    }


    public LocalDateTime readLclDtTm(LocalDateTime dValid) {
        LocalDateTime dDtTm = LocalDateTime.now();
        Integer nYYYY = 0;
        Integer nMM = 0;
        Integer nDD = 0;
        Integer nHH = 0;
        Integer nMi = 0;
        int[] paramInt = new int[1];
        while (true) {
            System.out.print("Год (ГГГГ): ");
            paramInt[0] = nYYYY;
            readNum(paramInt, dDtTm.getYear(), dDtTm.getYear() + 50);
            nYYYY = paramInt[0];

            System.out.print("Месяц (ММ): ");
            paramInt[0] = nMM;
            readNum(paramInt, 1, 12);
            nMM = paramInt[0];

            dDtTm = LocalDateTime.of(nYYYY, nMM, 01, 00, 00, 00);
            //System.out.printf("Last day of mounth=%d\n",dDtTm.plusMonths(1).minusDays(1).getDayOfMonth());

            System.out.print("День (ДД) : ");
            paramInt[0] = nDD;
            readNum(paramInt, 1, dDtTm.plusMonths(1).minusDays(1).getDayOfMonth());
            nDD = paramInt[0];

            System.out.print("Час (ЧЧ)  : ");
            paramInt[0] = nHH;
            readNum(paramInt, 1, dDtTm.plusMonths(1).minusDays(1).getDayOfMonth());
            nHH = paramInt[0];

            System.out.print("Минуты(мм): ");
            paramInt[0] = nMi;
            readNum(paramInt, 0, 59);
            nMi = paramInt[0];

            //LocalDateTime  dDtTm = LocalDateTime.now();
            dDtTm = LocalDateTime.of(nYYYY, nMM, nDD, nHH, nMi, 00, 00);
            if (dDtTm.compareTo(dValid) == -1) {
                System.out.println((char) 27 + "[31m" + "Дата и время уже прошли" + (char) 27 + "[37m");
                continue;
            } else {
                break;
            }
        }

        return dDtTm;
    }
    public int readInterval() {
        int interval = 0;
        int[] paramInt = new int[1];
        System.out.print("Интервал, cек: ");
        paramInt[0] = interval;
        readNum(paramInt, 0,60 * 60 * 24);
        interval = paramInt[0];
        return interval;
    }
    /**
     * ввод чисел с проверкой диапзона
     *
     * @param nNumParam
     * @param from
     * @param to
     */
    private void readNum(int[] nNumParam, int from, int to) {
        int nNum;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String line = reader.readLine();
                nNum = Integer.parseInt(line);
                if (nNum >= from && nNum <= to) {
                    nNumParam[0] = nNum;
                    break;
                } else {
                    System.out.println((char) 27 + "[31mДиапазон: "
                            + " " + from + " - " + to + (char) 27 + "[37m");
                    continue;
                }
            } catch (IOException e) {
                System.out.println((char) 27 + "[31mОшибка ввода вывода" + (char) 27 + "[37m");
                continue;
                //e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println((char) 27 + "[31mТолько цифры:"
                        + " " + from + " - " + to + (char) 27 + "[37m");
                continue;
            }
        }
    }

    public String toStringTask(String title, LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime,
                           int interval, boolean repeated, boolean active) {

        return "Задание : " + title + (
                (!repeated) ?
                        ("\n Время : " + time + "\n") :
                        "\n Время начала : " + startTime + "\n время конца : " + endTime + "\n"
                                + "интервал повторения " + repeated + "\n"
        );
    }
}
