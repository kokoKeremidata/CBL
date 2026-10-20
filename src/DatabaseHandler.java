package src;

import java.io.*;

public class DatabaseHandler {
    //save to database
    private static final String file_name = "anime.dat";
    public static void save(AnimeDatabase db) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(file_name))) {
            out.writeObject(db);
        }
    }
    
    //load the database
    public static AnimeDatabase load() throws IOException, 
            ClassNotFoundException {
        File file = new File(file_name);
        System.out.println(new File("anime.dat").getAbsolutePath());
        if (!file.exists()) {
            AnimeDatabase db = new AnimeDatabase();
            save(db);
            return db;
        }
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(file_name))) {
            return (AnimeDatabase) in.readObject();
        }
    }
}