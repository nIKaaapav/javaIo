package fileManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    @BeforeEach()
    void initializedAllFiles() throws IOException {
        File dataTest = new File("data/testOne");
        dataTest.mkdirs();

        File dataTestFile2 = new File("data/testOne/test.txt");
        dataTestFile2.createNewFile();

        File dataTest2 = new File("data/testTwo/testThree");
        dataTest2.mkdirs();

        File dataTestFile = new File("data/testTwo/testThree/test2.txt");
        dataTestFile.createNewFile();


        File dataTest3 = new File("data/testFour/testFive");
        dataTest3.mkdirs();

        File dataTest4= new File("data/testFour/testFive/test3.txt");
        dataTest4.createNewFile();
    }

    @AfterEach()
    void deleteTestData(){
        File dataTestFile3 = new File("data/testFour/testFive/test3.txt");
        dataTestFile3.delete();

        File dataTestFile2 = new File("data/testTwo/testThree/test2.txt");
        dataTestFile2.delete();

        File dataTestFile = new File("data/testOne/test.txt");
        dataTestFile.delete();

        File dataTest3 = new File("data/testTwo/testThree");
        dataTest3.delete();

        File dataTest = new File("data/testOne");
        dataTest.delete();

        File dataTest5 = new File("data/testFour/testFive");
        dataTest5.delete();

        File dataTest4 = new File("data/testFour");
        dataTest4.delete();

        File dataTest2 = new File("data/testTwo");
        dataTest2.delete();

        File dataTestRoot = new File("data");
        dataTestRoot.delete();
    }

    @Test
    void countFilesWorkCorrect(){
        int dataActual = FileManager.countFiles("data");
        assertEquals(3,dataActual);
    }

    @Test
    void countDirsWorkCorrect(){
        int dataActual = FileManager.countDirs("data");
        assertEquals(5,dataActual);
    }

    @Test
    void copyFileWorkCorrect() throws IOException {
        FileManager.copy("data/testOne/test.txt", "data/testTwo/testThree/test3.txt");
        File file = new File("data/testTwo/testThree/test3.txt");
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    void copyDirectoryWorkCorrect() throws IOException {
        FileManager.copy("data/testOne", "data/testTwo");
        File file = new File("data/testTwo/testOne");
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    void moveDirectoryWorkCorrect() throws IOException {
        FileManager.move("data/testOne", "data/testTwo");
        File fileActual = new File("data/testTwo/testOne");
        File file = new File("data/testOne");
        assertTrue(fileActual.exists());
        assertFalse(file.exists());
        file.delete();
    }

    @Test
    void moveFileWorkCorrect() throws IOException {
        FileManager.move("data/testOne/test.txt", "data/testTwo");
        File file = new File("data/testTwo/test.txt");
        assertTrue(file.exists());
        file.delete();
    }
}

