package hw9plus10plus11;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
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

        System.out.println("=====  ORIGINAL SAVES BEGIN =====");
        GameProgress gpOne = new GameProgress(100, 2, 10, 4000);
        System.out.println(gpOne);
        GameProgress gpTwo = new GameProgress(90, 4, 15, 8000);
        System.out.println(gpTwo);
        GameProgress gpThree = new GameProgress(50, 8, 20, 14000);
        System.out.println(gpThree);
        System.out.println("=====  ORIGINAL SAVES END =====");

        addSave(savegames, gpOne);
        addSave(savegames, gpTwo);
        addSave(savegames, gpThree);
        zipSaves(savegames);
        unzipSaves(savegames);
        List<GameProgress> loadedGameProgress = loadSave(savegames);
        System.out.println("=====  LOADED SAVES BEGIN =====");
        for (GameProgress gp : loadedGameProgress) {
            System.out.println(gp);
        }
        System.out.println("=====  LOADED SAVES END =====");

    }

    public static void writeLogs(Path path, String logs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
            writer.write(logs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addSave(Folder saveFolder, GameProgress gp) {
        String saveFileName = "save_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss_SSS")) + ".dat";
        String saveFilePath = saveFolder.addFiles(saveFileName).toString();

        try (FileOutputStream fos = new FileOutputStream(saveFilePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<GameProgress> loadSave(Folder saveFolder) {
        List<GameProgress> gpList = new ArrayList<>();
        File saveDir = saveFolder.getPath().toFile();
        File[] saveList = saveDir.listFiles();

        for (File saveFile : saveList) {
            try (FileInputStream fis = new FileInputStream(saveFile.getAbsolutePath());
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                GameProgress gp = (GameProgress) ois.readObject();
                gpList.add(gp);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return gpList;
    }

    public static void zipSaves(Folder saveFolder) {
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

    public static void unzipSaves(Folder saveFolder) {
        File saveDir = saveFolder.getPath().toFile();
        File[] saveDirFileList = saveDir.listFiles();
        File savesArchive = saveDirFileList[0];

        try (FileInputStream fis = new FileInputStream(savesArchive);
             ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                Path unzippedSave = saveFolder.addFiles(ze.getName());
                Files.copy(zis, unzippedSave, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for (File file : saveDirFileList) {
                if (file.getName().contains(".zip")) {
                    Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
