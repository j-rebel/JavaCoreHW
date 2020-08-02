package hw9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Folder {

    private static final String SR = System.getProperty("file.separator");
    private static StringBuilder log = new StringBuilder();

    private Path parent;
    private Path path;

    private List<Folder> subfolderList = new ArrayList<>();
    private List<Path> filesList = new ArrayList<>();

    public Folder(String parent, String path) {
        this.parent = Paths.get(parent).toAbsolutePath();
        this.path = Paths.get(parent + SR + path).toAbsolutePath();
        if (Files.exists(this.path)) {
            log.append("Already existing directory ");
            log.append(this.path);
            log.append("\n");
        } else {
            try {
                Files.createDirectory(this.path);
                log.append("Successfully created directory ");
                log.append(this.path);
                log.append("\n");
            } catch (IOException e) {
                log.append("Failed to create ");
                log.append(this.path);
                log.append(e.getLocalizedMessage());
                log.append("\n");
            }
        }
    }

    public Path getPath() {
        return this.path;
    }

    public static String getLog() {
        return log.toString();
    }

    public Folder addSubfolder(String subfolderPath) {
        Folder subfolder = new Folder(path.toString(), subfolderPath);
        subfolderList.add(subfolder);
        return subfolder;
    }

    public Path addFiles(String fileName) {
        Path filePath = Paths.get(path.toString() + SR + fileName).toAbsolutePath();
        filesList.add(filePath);
        if(Files.exists(filePath)) {
            log.append("Already existing file " + filePath + "\n");
            log.append(filePath);
            log.append("\n");
        } else {
            try {
                Files.createFile(filePath);
                log.append("Successfully created file ");
                log.append(filePath);
                log.append("\n");
            } catch (IOException e) {
                log.append("Failed to create file ");
                log.append(filePath);
                log.append(e.getLocalizedMessage());
                log.append("\n");
            }
        }
        return filePath;
    }

    public void printSubFoldersList() {
        if(subfolderList.isEmpty()) {
            System.out.println("- No subfolders inside.");
        } else {
            for (Folder subfolder : subfolderList) {
                System.out.println("- " + subfolder.getPath());
            }
        }
    }

    public void printFilesList() {
        if(filesList.isEmpty()) {
            System.out.println("- No files inside.");
        } else {
            for (Path filePath : filesList) {
                System.out.println("- " + filePath.toString());
            }
        }
    }
}
