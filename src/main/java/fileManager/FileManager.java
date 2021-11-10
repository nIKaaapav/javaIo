package fileManager;

import java.io.File;
import java.util.Arrays;

public class FileManager {
    private static int countFilesRecurces(File directory){
        return 0;
    }

    public static int countFiles(String path){
        int countFilesInAllDirectory = 0;
        File data = new File("data");

        System.out.println(data.isDirectory());
        for (File f:data.listFiles()) {
            System.out.print(f);
            System.out.println(f.isFile());
        }
        return countFilesInAllDirectory;
    }

    public static void main(String[] args) {
        countFiles("data");
    }
}
