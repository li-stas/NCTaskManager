package ua.edu.sumdu.j2se.lytovka.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Tasks {
    public static Iterable<Task> incoming333(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to)   {
        return new Iterable<Task>() {
            @Override
            public Iterator<Task> iterator( ) {
                return new Iterator<Task>() {
                    private Iterator<Task> it = tasks.iterator();
                    //private Task t = it.next();
                    @Override
                    public boolean hasNext( ) {
                        return it.hasNext();
                    }

                    @Override
                    public Task next( ) {
                        while (true) {

                            Task t = it.next();
                            //System.out.println("next->"+"f:"+from+" t:"+to+t);
                            if (isIncoming(t, from, to)) {
                                return t;
                            }
                            if (!hasNext()) {
                                break;
                            }
                        }
                        return null;
                    }
                };
            }
        };
    }

    public static Iterable<Task> incoming22(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        /*
        AbstractTaskList resList = new ArrayTaskList();
        for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
            Task t = it.next();
            //System.out.println(t);
            if (isIncoming(t, from, to)) {
                //System.out.println("t->"+t);
                resList.add(t);
            }
        }*/
        return new Iterable<Task>() {
            @Override
            public Iterator<Task> iterator( ) {
                return new Iterator<Task>() {
                    private Iterator<Task> it = tasks.iterator();
                    private Task t ;//; = new Task(); //next();

                    @Override
                    public boolean hasNext( ) {
                        while (true) {
                            if (it.hasNext()) {
                                if (isIncoming(t, from, to)) {
                                    return true;
                                }
                                System.out.println("next();");
                                next();
                                continue;
                            }
                            return false;
                        }
                    }

                    @Override
                    public Task next( ) {
                        t = it.next();
                        System.out.println("next->"+"f:"+from+" t:"+to+t);
                        return t;
                    }

                };
            }
         };
    }
    /*
                private Iterator<Task> it = tasks.iterator();
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Task next() {
                Task t = it.next();
                while (true) {
                    if (isIncoming(t, from, to)) {
                        return t;
                    }
                    t = it.next();
                }
            }

     */

    private static boolean isIncoming(Task elem, LocalDateTime from, LocalDateTime to) {
        LocalDateTime toTime = elem.nextTimeAfter(from);
        //if (elem.isActive() && toTime != -1 && (toTime < to || toTime == to )) {
        if (elem.isActive() && toTime != null
                && (toTime.compareTo(to) == -1 || toTime.compareTo(to) == 0 )) {
            return true;
        }
        return false;
    }
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        List<Task> resList = new ArrayList<>();
        for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
            Task t = it.next();
            if (isIncoming(t, from, to)) {
                resList.add(t);
            }
        }
        return (Iterable<Task>) resList;
    }
    public static Iterable<Task> incomingList(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        AbstractTaskList resList = new ArrayTaskList();
        for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
            Task t = it.next();
            //System.out.println(t);
            if (isIncoming(t, from, to)) {
                //System.out.println("t->"+t);
                resList.add(t);
            }
        }
        return (Iterable<Task>) resList;
    }
}
