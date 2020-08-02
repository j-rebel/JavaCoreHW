package hw9;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        Folder root = new Folder(System.getProperty("user.home"), "Games");
        Folder src = root.addSubfolder("src");
        Folder res = root.addSubfolder("res");
        Folder temp = root.addSubfolder("temp");
        Folder savegames = root.addSubfolder("savegames");

        src.addSubfolder("test");
        Folder main = src.addSubfolder("main");
        main.addFiles("Main.java");
        main.addFiles("Utils.java");

        res.addSubfolder("drawables");
        res.addSubfolder("vectors");
        res.addSubfolder("icons");

        Path LogFilePath = temp.addFiles("temp.txt");
        writeLogs(LogFilePath, Folder.getLog());

        GameProgress gpOne = new GameProgress(100, 2, 10, 4000);
        System.out.println(gpOne);
        GameProgress gpTwo = new GameProgress(90, 4, 15, 8000);
        System.out.println(gpTwo);
        GameProgress gpThree = new GameProgress(50, 8, 20, 14000);
        System.out.println(gpThree);

        writeNewSave(savegames, gpOne);
        writeNewSave(savegames, gpTwo);
        writeNewSave(savegames, gpThree);
        addSaveToZip(savegames);

    }

    public static void writeLogs(Path path, String logs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
            writer.write(logs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeNewSave(Folder saveFolder, GameProgress gp) {
        String saveFileName = "save_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss_SSS")) + ".dat";
        String saveFilePath = saveFolder.addFiles(saveFileName).toString();

        try (FileOutputStream fos = new FileOutputStream(saveFilePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addSaveToZip(Folder saveFolder) {
        String saveArchiveName = "saves_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".zip";
        File saveDir = saveFolder.getPath().toFile();
        File[] saveFiles = saveDir.listFiles();
        String saveZipName = saveFolder.addFiles(saveArchiveName).toString();
        try (FileOutputStream fos = new FileOutputStream(saveZipName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            for (File saveFile : saveFiles) {
                if(!saveFile.getName().contains(".zip")) {
                    zos.putNextEntry(new ZipEntry(saveFile.getName()));
                    Files.copy(saveFile.toPath(), zos);
                }
                Files.deleteIfExists(saveFile.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    public static void loadSave(Folder saveFolder, GameProgress gp) {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Username\\Desktop\\save.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        GameProgress savedGame = (GameProgress) objectInputStream.readObject();

        System.out.println(savedGame);
    }*/
}
