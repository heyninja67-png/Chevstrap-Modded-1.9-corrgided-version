package com.chevstrap.rbx.Utility;

import com.chevstrap.rbx.Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileToolAlt {

    // Detect if the OS is LineageOS (optional, for debugging/logging purposes)
    public static boolean isLineageOS() {
        String display = android.os.Build.DISPLAY;
        String product = android.os.Build.PRODUCT;

        return (display != null && display.toLowerCase().contains("lineage")) ||
                (product != null && product.toLowerCase().contains("lineage"));
    }

    public static boolean isRootAvailable() {
        try {
            Process process = Utilities.ShellExecute(new String[]{"su", "-c", "echo rooted"});
            assert process != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            process.destroy();

            boolean isRooted = "rooted".equals(line);
            if (isLineageOS()) {
                return isRooted;
            }
            return isRooted;
        } catch (Exception e) {
            if (isLineageOS()) {
                return false;
            }
            return false;
        }
    }

    public static String runRootCommand(String command) throws IOException {
        Process process = Utilities.ShellExecute(new String[]{"su", "-c", command});
        StringBuilder output = new StringBuilder();

        assert process != null;
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            while ((line = error.readLine()) != null) {
                output.append("ERR: ").append(line).append("\n");
            }
            process.waitFor();
        } catch (Exception e) {
            throw new IOException("Failed to execute root command", e);
        }

        return output.toString();
    }

    public static void createDirectoryWithPermissions(String path) throws IOException {
        runRootCommand("mkdir -p \"" + path + "\" && chmod -R 755 \"" + path + "\"");
    }

    public static boolean pathExists(String path) {
        if (!isRootAvailable()) {
            return new File(path).exists();
        }
        try {
            String result = runRootCommand("ls \"" + path + "\"");
            return !result.contains("No such file");
        } catch (Exception e) {
            return false;
        }
    }

    public static void writeFile(String path, String content) throws IOException {
        if (!isRootAvailable()) {
            File file = new File(path);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("Non-root: Failed to create parent directory for " + path);
            }
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
            }
            return;
        }

        File tempFile = File.createTempFile("temp", null);
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }

        runRootCommand("cat \"" + tempFile.getAbsolutePath() + "\" > \"" + path + "\"");
        runRootCommand("chmod 644 \"" + path + "\"");

        if (!tempFile.delete()) {
            throw new IOException("Failed to delete temp file: " + tempFile.getAbsolutePath());
        }
    }

    public static boolean isDirectory(String path) throws IOException {
        if (!isRootAvailable()) {
            return new File(path).isDirectory();
        }
        String result = runRootCommand("[ -d \"" + path + "\" ] && echo dir || echo file").trim();
        return result.equals("dir");
    }

    public static boolean isExists(String path) throws IOException {
        if (!isRootAvailable()) {
            return new File(path).exists();
        }
        String result = runRootCommand("[ -e \"" + path + "\" ] && echo exists || echo missing").trim();
        return result.equals("exists");
    }

    public static File[] listFiles(String directoryPath) throws IOException {
        if (!isRootAvailable()) {
            File dir = new File(directoryPath);
            return dir.exists() ? dir.listFiles() : new File[0];
        }

        String output = runRootCommand("ls -1 \"" + directoryPath + "\"");
        List<File> fileList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new StringReader(output))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.startsWith("ERR:")) {
                    fileList.add(new File(directoryPath, line.trim()));
                }
            }
        }

        return fileList.toArray(new File[0]);
    }

    public static String readFile(String path) throws IOException {
        if (!isRootAvailable()) {
            File file = new File(path);
            if (!file.exists()) throw new IOException("Non-root: File does not exist: " + path);
            StringBuilder builder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
            }
            return builder.toString();
        }

        return runRootCommand("cat \"" + path + "\"");
    }
}