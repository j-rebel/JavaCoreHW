package hw9;

import java.io.*;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        writeLogs(createStructureAndReturnLogPath(), Folder.getLog());
    }

    public static Path createStructureAndReturnLogPath() {
        Folder root = new Folder(System.getProperty("user.home"), "Games");
        Folder src = root.addSubfolder("src");
        Folder res = root.addSubfolder("res");
        Folder temp = root.addSubfolder("temp");
        root.addSubfolder("savegames");

        src.addSubfolder("test");
        Folder main = src.addSubfolder("main");
        main.addFiles("Main.java");
        main.addFiles("Utils.java");

        res.addSubfolder("drawables");
        res.addSubfolder("vectors");
        res.addSubfolder("icons");

        return temp.addFiles("temp.txt");
    }

    public static void writeLogs(Path path, String logs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
            writer.write(logs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
