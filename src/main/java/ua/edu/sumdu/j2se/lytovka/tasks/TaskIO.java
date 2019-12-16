package ua.edu.sumdu.j2se.lytovka.tasks;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import com.google.gson.stream.JsonWriter;

import java.io.*;

import java.time.*;



public class TaskIO {
    static ZoneId zoneId = ZoneId.systemDefault();
    /**
     * – записує задачі із списку у потік у бінарному форматі, описаному нижче.
     * Классы DataOutputStream и DataInputStream позволяют записывать и считывать данные примитивных типов.
     * https://metanit.com/java/tutorial/6.7.php
     */
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        //System.out.println(tasks);
        //System.out.println("============= праметр ==================");
        //tasks.getStream().forEach(t->System.out.println(t));
        //System.out.println("============= чз Стрим ==================");
        //ZoneId zoneId = ZoneId.systemDefault();
        DataOutputStream dos = new DataOutputStream(out);
        try {
            dos.writeInt(tasks.size()); // всего задач
            tasks.getStream().forEach(t-> dosWriteTask(t,dos));
        } finally {
            dos.flush();
            dos.close();
        }
        //System.out.println("============= dosWriteInt ==================");
    }

    /**
     *  Ввод-вывод в Java. Классы FileInputStream, FileOutputStream, BufferedInputStream
     * https://javarush.ru/groups/posts/2020-vvod-vihvod-v-java-klassih-fileinputstream-fileoutputstream-bufferedinputstream
     *
     * Преобразование миллисекунд в LocalDateTime в Java 8
     * https://howtoprogram.xyz/2017/02/11/convert-milliseconds-localdatetime-java/
     *  @param t
     * @param dos
     */

    private static void dosWriteTask(Task t, DataOutputStream dos) {
        try {
            //System.out.println(t);
            dos.writeInt(t.getTitle().length());
            dos.writeUTF(t.getTitle());
            dos.writeBoolean(t.isActive());
            dos.writeInt(t.getRepeatInterval());
            if (t.getRepeatInterval() != 0) {
                dos.writeLong(t.getStartTime().atZone(zoneId).toInstant().toEpochMilli());
                dos.writeLong(t.getEndTime().atZone(zoneId).toInstant().toEpochMilli());
            } else {
                dos.writeLong(t.getTime().atZone(zoneId).toInstant().toEpochMilli());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * – зчитує задачі із потоку у даний список задач.
      */
    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);
        try{
            int countTask = dis.readInt();
            for (int i = 1; i <= countTask; i++) {
                Task task;
                int lenTitle = dis.readInt();
                String title = dis.readUTF();
                boolean active = dis.readBoolean();
                int interval = dis.readInt();
                LocalDateTime startTime = Instant.ofEpochMilli(dis.readLong()).atZone(zoneId).toLocalDateTime();
                if (interval != 0) {
                    LocalDateTime endTime  = Instant.ofEpochMilli(dis.readLong()).atZone(zoneId).toLocalDateTime();
                    task = new Task(title, startTime, endTime, interval);
                } else {
                    task = new Task(title, startTime);
                }
                task.setActive(active);
                tasks.add(task);
                //System.out.println(task);
            }
        } finally {
            dis.close();
        }
        //System.out.println("============= read ==================");
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

    /**
     * Иcтoчниk: https://j4web.ru/java-json/model-dannyh-gson-java-obekty-i-json.html
     * https://stackoverflow.com/questions/39192945/serialize-java-8-localdate-as-yyyy-mm-dd-with-gson
     * – записує задачі зі списку у потік в форматі JSON.
     */
    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        //System.out.println(tasks);
        //System.out.println("============= праметр ==================");
        Gson gson;
        gson =  new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .create();
        String sGson = gson.toJson(tasks);

        //System.out.println(sGson);
        //System.out.println("============= sGson ==================");

        try {
            out.write(sGson);
        } finally {
            out.flush();
            out.close();
        }
    }


    /**
     * – зчитує задачі із потоку у список.
     */
    public static void read(AbstractTaskList tasks, Reader in) {
        //Gson gson = new Gson();
        Gson gson;
        /*gson =  new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .create();*/
        gson =  new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter() {
                    @Override
                    public void write(JsonWriter jsonWriter, LocalDateTime localDate) throws IOException {
                        jsonWriter.value(localDate.toString());
                    }
                    @Override
                    public LocalDateTime read(JsonReader jsonReader) throws IOException {
                        return LocalDateTime.parse(jsonReader.nextString());
                    }
                        }
                )
                .create();
        ArrayTaskList tasksA =  gson.fromJson(in, ArrayTaskList.class);
       // tasks = (AbstractTaskList) tasksA;
        tasksA.getStream().forEach(t-> tasks.add(t));
       // System.out.println(tasks);
       // System.out.println("============= read Json ==================");
    }

    /**
     * – записує задачі у файл у форматі JSON
     */
    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        //fileOutputStream - fos
        FileOutputStream fos = new FileOutputStream(file);
        try {
            write(tasks,fos);
        } finally {
            fos.close();
        }
    }

    /**
     * – зчитує задачі із файлу.
     */
    public static void readText(AbstractTaskList tasks, File file) throws IOException {
    // fis - FileInputStream
        FileInputStream fis = new FileInputStream(file);
        try {
            read(tasks, fis);
        } finally {
            fis.close();
        }
    }

    private static class LocalDateAdapter extends TypeAdapter<LocalDateTime> {
        @Override
        public void write(JsonWriter jsonWriter, LocalDateTime localDate) throws IOException {
            jsonWriter.value(localDate.toString());
        }
        @Override
        public LocalDateTime read(JsonReader jsonReader) throws IOException {
            return LocalDateTime.parse(jsonReader.nextString());
        }
    }
    /*
    GSON GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
    ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime()).create();
     */
    /*
    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
     @Override
     public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
           return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime();
      }
      }).create();
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
        };
    }
     */

}



