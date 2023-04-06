package pl.org.smartsolution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WyszukiwanieZnakow {

    private static final String[] PUNCTUATION_MARKS = { "_", "'", ",", "/", "\"", "\\." };

    public static void main(String[] args) {
        String rootDirectoryPath = "C:\\test";
        File rootDirectory = new File(rootDirectoryPath);
        List<File> filesToRename = new ArrayList<File>();
        List<File> directoriesToRename = new ArrayList<File>();
        collectFilesAndDirectories(rootDirectory, filesToRename, directoriesToRename);
        saveDirectoriesToFile(directoriesToRename, rootDirectoryPath);
    }

    private static void collectFilesAndDirectories(File root, List<File> files, List<File> directories) {
        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                directories.add(file);
                collectFilesAndDirectories(file, files, directories);
            } else {
                files.add(file);
            }
        }
    }

    private static void saveDirectoriesToFile(List<File> directories, String rootDirectoryPath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(rootDirectoryPath + File.separator + "zdublowane.txt"));
            for (File directory : directories) {
                for (String punctuationMark : PUNCTUATION_MARKS) {
                    if (directory.getName().contains(punctuationMark)) {
                        writer.write(directory.getAbsolutePath());
                        writer.newLine();
                        break;
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file");
            e.printStackTrace();
        }
    }

}
