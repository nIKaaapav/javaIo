package fileManager;

import java.io.*;

public class FileManager {
    private static int countFilesRecurces(File file) {
        int countFilesInAllDirectory = 0;

        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                countFilesInAllDirectory += countFilesRecurces(f);
            } else if (f.isFile()) {
                countFilesInAllDirectory++;
            }
        }

        return countFilesInAllDirectory;
    }

    public static int countFiles(String path) {
        File data = new File(path);
        return countFilesRecurces(data);
    }

    private static int countDirsRecurces(File file) {
        int countDirs = 0;
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                countDirs++;
                countDirs += countDirsRecurces(f);
            }
        }
        return countDirs;
    }

    public static int countDirs(String path) {
        int countDirs = 0;
        File data = new File(path);

        if (data.isFile()) {
            return countDirs;
        }

        return countDirsRecurces(data);
    }

    private static void copyFile(File from, File to) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(from);
             FileOutputStream fileOutputStream = new FileOutputStream(to);) {
            byte[] buf = new byte[1024];
            int value;
            while ((fileInputStream.read()) > 0) {
                value = fileInputStream.read(buf, 0, buf.length);
                fileOutputStream.write(buf, 0, value);
            }
        }
    }

    private static void copyDirectory(File from, File to) throws IOException {
        if (to.exists()) {
            to.mkdir();
        }

        for (File file : from.listFiles()) {
            if (file.isDirectory()) {
                File fileCurrent = copyDir(from, to);
                copyDirectory(file, fileCurrent);
            } else {
                File fileName = new File(to + "/" + file.getName());
                copyFile(file, fileName);
            }
        }

    }

    public static void copy(String from, String to) throws IOException {
        File fileFrom = new File(from);
        File fileTo = new File(to);

        if (fileFrom.isFile()) {
            copyFile(fileFrom, fileTo);
        }
        if (fileFrom.isDirectory()) {
            File file = copyDir(fileFrom, fileTo);
            copyDirectory(fileFrom, file);
        }
    }

    private static File copyDir(File from, File to) {
        File file = new File(to + "/" + from.getName());
        file.mkdir();
        return file;
    }

    public static void move(String from, String to) throws IOException {
        File fileFrom = new File(from);
        File fileTo = new File(to);
        String toPatch = to;
        if (fileTo.isDirectory() && fileFrom.isFile()){
            toPatch = fileTo.getPath() + "/" + fileFrom.getName();
        }
        copy(from, toPatch);
        remove(fileFrom);
    }

    private static void remove(File file) {
        if (file.isDirectory()) {
            for (File fileChildren : file.listFiles()) {
                fileChildren.delete();
                if (fileChildren.isDirectory()){
                    remove(fileChildren);
                }
            }
            file.delete();
        } else if (file.isFile()) {
            file.deleteOnExit();
        }
    }
}
