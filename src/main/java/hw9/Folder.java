package hw9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Folder {

    private static final String SR = System.getProperty("file.separator");
    private static StringBuilder log = new StringBuilder();

    private Path parent;
    private Path path;

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
        return new Folder(path.toString(), subfolderPath);
    }

    public Path addFiles(String fileName) {
        Path filePath = Paths.get(path.toString() + SR + fileName).toAbsolutePath();
        if(Files.exists(filePath)) {
            log.append("Already existing file ");
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
}
