package hw9plus10plus11;

import org.junit.Test;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FolderTest {
    private static Folder testFolder = new Folder("", "testFolder");
    private static Folder testSubFolder = testFolder.addSubfolder("testSubFolder");

    @Test
    public void createFolder() {
        Assertions.assertTrue(Files.exists(testFolder.getPath()) && Files.isDirectory(testFolder.getPath()));
    }

    @Test
    public void addSubfolder() {
        Assertions.assertTrue(Files.exists(testSubFolder.getPath()) && Files.isDirectory(testSubFolder.getPath()));
    }

    @Test
    public void subfolderParentEqualsParentPath() {
        Assertions.assertEquals(testFolder.getPath().toString(), testSubFolder.getPath().getParent().toString());
    }

    @Test
    public void addFile() {
        Assertions.assertTrue(Files.exists(testSubFolder.addFiles("test.txt")));
    }

    @Test
    public void checkSubFilesListSize() {
        File[] files = testSubFolder.getPath().toFile().listFiles();
        assertThat(files, arrayWithSize(1));
    }

    @Test
    public void checkSubFilesListContainsAdded() {
        File[] files = testSubFolder.getPath().toFile().listFiles();
        assertThat(files, hasItemInArray(new File(testSubFolder.getPath().toString() + "\\test.txt")));
    }

}
