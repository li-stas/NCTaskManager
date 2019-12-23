package ua.edu.sumdu.j2se.lytovka.tasks.view;


import ua.edu.sumdu.j2se.lytovka.tasks.model.Task;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.Menu;
import ua.edu.sumdu.j2se.lytovka.tasks.view.menuto.MenuEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;

public class TasksView {
    public int menuReadTast(Task t) {
        int nRet;
        Menu menu = new Menu(1);
        menu.addEntry(new MenuEntry( String.format("%-25s","1 - Название") + ":" + t.getTitle(),
                true) {
            public void run() {                //System.out.println("test1 run");
            }
        });
        menu.addEntry(new MenuEntry( String.format("%-25s","2 - Актинвная") + ":" + (t.isActive() ? "Да" : "Нет"),
                true) {
            public void run() {                //System.out.println("test1 run");
            }
        });
        if (t.isRepeated()) {
            menu.addEntry(new MenuEntry( String.format("%-25s","3 - Время начала") + ":" + t.getStartTime(),
                    true) {
                public void run() {                //System.out.println("test1 run");
                }
            });
            menu.addEntry(new MenuEntry( String.format("%-25s","4 - Время окончания") + ":" + t.getEndTime(),
                    true) {
                public void run() {                //System.out.println("test1 run");
                }
            });
            menu.addEntry(new MenuEntry( String.format("%-25s","5 - Интервал повторения") + ":" + t.getRepeatInterval(),
                    true) {
                public void run() {                //System.out.println("test1 run");
                }
            });
        } else {
            menu.addEntry(new MenuEntry( String.format("%-25s","3 - Время начала") + ":" + t.getStartTime(),
                    true) {
                public void run() {                //System.out.println("test1 run");
                }
            });
        }
        nRet = menu.run();
        return nRet;
    }

    public int menu00() {
        int nRet;
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry( "1 - Редактировать", true) {
            @Override
            public void run() {                //System.out.println("test1 run");
            }
        });
        menu.addEntry(new MenuEntry("2 - Добавить", true) {
            @Override
            public void run() {                //System.out.println("test2 run");
            }
        });
        menu.addEntry(new MenuEntry("3 - Удалить задание", true) {
            @Override
            public void run() {                //System.out.println("test3 run");
            }
        });
        menu.addEntry(new MenuEntry("4 - Календарь на период", true) {
            @Override
            public void run() {                //System.out.println("test4 run");
            }
        });
        System.out.print("Что хотите сделать заданиями? ");
        nRet = menu.run();
        return nRet;

    }

    public void doSayMess(String cMess) {
        System.out.printf(cMess);
    }

    public void doSayMess_R$B(String cMess) {
        System.out.printf((char)27 + "[31m" + cMess + (char)27 + "[37m");
    }

    public void doSrcTasks(Iterator<Task> iterator) {
        doSayMess("\n");
        int i = 0;
        for (;iterator.hasNext();) {
            Task t = iterator.next();
            String cMess = String.format("%2d", ++i)
                    + ". [ "
                    + (t.isActive() ? (char)27 + "[30m" : "")
                    + toStringTaskShort(t.getTitle(), t.getTime(), t.getStartTime(), t.getEndTime(),
                    t.getRepeatInterval(), t.isRepeated(), t.isActive())
                    +  (char)27 + "[37m"
                    + " ]";
            doSayMess( cMess + '\n');
        }
        doSayMess("\n");
    }
    public void doSrcMaxTasks() {
        doSayMess("\n");
        doSayMess((char) 27 + "[31m" + "К-во задач ограничено 10" + (char)27 + "[37m"+"\n");
        doSayMess("\n");
    }


    public void doSrcEmptyTasks() {
        doSayMess("\n");
        doSayMess((char) 27 + "[33m" + "Список заданий пустой"+ (char)27 + "[37m"+"\n");
        doSayMess("\n");
    }

    public String readTitle() {
        Scanner scan = new Scanner(System.in);
        doSayMess("Название задания: ");
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

    public int readDoRemoveTask() {
        System.out.print("Задание удалить? ");
        return readYesNo();
    }

    public int readIsTaskRepit() {
        System.out.print("Задание Повторяется? ");
        return readYesNo();
    }

    public int readIsTaskActive() {
        System.out.print("Задание Активна? ");
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
        //int[] paramInt = new int[1];
        while (true) {

            //            System.out.print("    Год (ГГГГ): ");
            //            paramInt[0] = nYYYY;
            //            readNum(paramInt, dDtTm.getYear(), dDtTm.getYear() + 50);
            //            nYYYY = paramInt[0];
            nYYYY = readNumSayGetValid("    Год (ГГГГ): ", dDtTm.getYear(), dDtTm.getYear() + 50);
            //            System.out.print("    Месяц (ММ): ");
            //            paramInt[0] = nMM;
            //            readNum(paramInt, 1, 12);
            //            nMM = paramInt[0];
            nMM = readNumSayGetValid("    Месяц (ММ): ",  1, 12);

            dDtTm = LocalDateTime.of(nYYYY, nMM, 01, 00, 00, 00);

            /*System.out.print("    День (ДД) : ");
            paramInt[0] = nDD;
            readNum(paramInt, 1, dDtTm.plusMonths(1).minusDays(1).getDayOfMonth());
            nDD = paramInt[0];             */
            nDD = readNumSayGetValid("    День (ДД) : ", 1, dDtTm.plusMonths(1).minusDays(1).getDayOfMonth());
            /*            System.out.print("    Час (ЧЧ)  : ");
            paramInt[0] = nHH;
            readNum(paramInt, 0, 24);
            nHH = paramInt[0];             */
            nHH = readNumSayGetValid("    Час (ЧЧ)  : ", 0, 23);
            /*System.out.print("    Минуты(мм): ");
            paramInt[0] = nMi;
            readNum(paramInt, 0, 59);
            nMi = paramInt[0];     */
            nMi = readNumSayGetValid("    Минуты(мм): ", 0, 59);

            //LocalDateTime  dDtTm = LocalDateTime.now();
            dDtTm = LocalDateTime.of(nYYYY, nMM, nDD, nHH, nMi, 00, 00);
            if (dDtTm_compareTo(dDtTm, dValid)) {
                break;
            } else {
                continue;
            }

        }

        return dDtTm;
    }
    public boolean dDtTm_compareTo(LocalDateTime dDtTm, LocalDateTime dValid) {
        if (dDtTm.compareTo(dValid) < 0) {
            System.out.println((char) 27 + "[31m" + "Дата и время уже прошли" + (char) 27 + "[37m");
            return false;
        } else {
            return true;
        }
    }

    public boolean dDtTm_compare02(LocalDateTime dDtTm, LocalDateTime dValid) {
        if (dDtTm.compareTo(dValid) > 0) {
            System.out.println((char) 27 + "[31m" + "Дата и время должна быть меньше или равна Времени окончания" + (char) 27 + "[37m");
            return false;
        } else {
            return true;
        }
    }
    public LocalDateTime readStartTime(LocalDateTime d4Valid) {
        System.out.println("Время начала: ");
        return readLclDtTm(d4Valid);
    }
    public LocalDateTime readEndTime(LocalDateTime d4Valid) {
        System.out.println("Время оконончания: ");
        return readLclDtTm(d4Valid);
    }
    public int readInterval() {
        return readNumSayGetValid("Интервал, cек: ", 0,60 * 60 * 24);
    }

    public int readWhatTaskNumber(int nMaxTask) {
        return readNumSayGetValid("Выбирете номер задания 0 - " + nMaxTask + " (0 - Отмена):", 0,nMaxTask);
    }

    public int readNumSayGetValid(String s1, int from, int to) {
        int[] paramInt = new int[1];
        paramInt[0] = 0;
        System.out.print(s1);
        readNum(paramInt, from, to);
        return paramInt[0];
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
                doSrcIOException();
                continue;
                //e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println((char)27 + "[31m" + "Только цифры:"
                        + " " + from + " - " + to + (char)27 + "[37m");
                continue;
            }
        }
    }

    public String toStringTask(String title, LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime,
                           int interval, boolean repeated, boolean active) {

        return "Задание: " + String.format("%-35s",title) + (
                (!repeated) ?
                        ("\n Время: " + dToC(time) + "\n") :
                        "\n Время начала: " + dToC(startTime) + "\n время конца: " + dToC(endTime) + "\n"
                                + " интервал повторения: " + interval + "\n"
        );
    }
    public String toStringTaskShort(String title, LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime,
                               int interval, boolean repeated, boolean active) {

        return "Зд: " + String.format("%-15s", title) + (
                (!repeated) ?
                        (" Вp : " + dToC(time) ) :
                        " ВpH: " + dToC(startTime) + " ВpК: " + dToC(endTime)  + " ИнП: " + interval // + "\n"
        );
    }

    private String dToC(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return  now.format(formatter);
    }

    public void doSrcIOException( ) {
        System.out.println((char) 27 + "[31mОшибка ввода вывода" + (char) 27 + "[37m");
    }

}
