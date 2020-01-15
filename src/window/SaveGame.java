package window;

import levels.WorldSave;

import java.io.*;

import java.io.*;
import java.net.URI;

    public class SaveGame {

        public static final String filename = "/SaveData001.sav";
        public static final String root = "/Blockbusters";

        public static void save(Serializable objectToSerialise) {
            System.out.println("GDAA");

            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(createDataFolder() + filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(objectToSerialise);
                oos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static WorldSave load() {
            if (checkFileExists()) {

                FileInputStream fis = null;
                WorldSave loadedObject = null;

                try {
                    fis = new FileInputStream(createDataFolder() + filename);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    loadedObject = (WorldSave) ois.readObject(); //TODO: WSO
                    ois.close();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                return loadedObject;
            }
            return null;
        }


        public static boolean checkFileExists() {

            return new File(createDataFolder() + filename).isFile();
        }


        public static String createDataFolder(){


            String home = System.getProperty("user.home");
            String os = System.getProperty("os.name").toLowerCase();
            System.out.println(os);


            if (os.contains("win")) {
                home = System.getenv("appdata");
            } else if (os.contains("mac")) {
                home += "~/Library/Application Support";
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                home += "~/.";
            }

            File dir = new File(home);
            dir = new File(dir, root);

            if (!dir.exists()) {

                dir.mkdir();

                System.out.println("Creating Directory!");
            }
            return dir.getAbsolutePath();
        }

        private static boolean deleteSaveFile() {

            if (!checkFileExists()) {

                System.err.println("File: " + createDataFolder() + filename + " does not exist!");
                return new File(createDataFolder()).delete();

            }
            File toDelete = new File(createDataFolder() + filename);

            if (toDelete.canWrite()) {
                return toDelete.delete();
            }
            System.err.println("File " + createDataFolder() + filename + "is write protected!");
            return false;
        }


    }


