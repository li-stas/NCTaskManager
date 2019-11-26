package ua.edu.sumdu.j2se.lytovka.tasks;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Iterator;


public class TaskIO {
    /**
     * – записує задачі із списку у потік у бінарному форматі, описаному нижче.
     */
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        //tasks.getStream().forEach(t->System.out.println(t));
        DataOutputStream dos = new DataOutputStream(out);
        try {
            dos.writeInt(tasks.size());
            tasks.getStream().forEach(t-> dosWriteInt(t,dos));
        } finally {
            dos.flush();
            dos.close();
        }

    }

    private static void dosWriteInt(Task t, DataOutputStream dos) {
        try {
            dos.writeInt(t.getTitle().length());
            dos.writeUTF(t.getTitle());
            dos.writeBoolean(t.isActive());
            dos.writeInt(t.getRepeatInterval());
            if (t.isRepeated()) {
                dos.writeBytes(String.valueOf(t.getStartTime()));
                dos.writeBytes(String.valueOf(t.getEndTime()));
            } else {
                dos.writeBytes(String.valueOf(t.getTime()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * – зчитує задачі із потоку у даний список задач.
      */
    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream inputStream = new DataInputStream(in);
        try{
            int count = inputStream.readInt();
            for(int i = 0; i > count; i++){
                Task task;
                int length = inputStream.readInt();
                String title = inputStream.readUTF();
                boolean active = inputStream.readBoolean();
                int interval = inputStream.readInt();
                 LocalDateTime startTime ; //= new LocalDateTime(inputStream.readLong());
                if (interval > 0) {
                    LocalDateTime endTime ;//= new Date(inputStream.readLong());
                    //task = new Task(title, startTime, endTime, interval);
                } else {
                    //task = new Task(title, startTime);
                }
                //task.setActive(active);
                //tasks.add(task);
            }
        } finally {
            inputStream.close();
        }
    }
    /**
     * – записує задачі із списку у файл.
     */
    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        //fileOutputStream - fos
        FileOutputStream fos = new FileOutputStream(file);
        try {
            write(tasks,fos);
        } finally {
            fos.close();
        }
    }
    /**
     * – зчитує задачі із файлу у список задач.
     */
    public static void readBinary(AbstractTaskList tasks, File file) throws IOException {
        // fis - FileInputStream
        FileInputStream fis = new FileInputStream(file);
        try {
            read(tasks, fis);
        } finally {
            fis.close();
        }
    }
}



