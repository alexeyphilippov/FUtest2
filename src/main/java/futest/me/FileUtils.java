package futest.me;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class FileUtils {

    private FileUtils() {
    }

    public static void delete(String path) throws FileNotFoundException, NullPointerException {
        if (Objects.isNull(path)) {
            throw new IllegalArgumentException("Path should not be null");
        }
        File file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException("This might be a directory");
        }
        if (file.exists()) {
            file.delete();
        } else if (!file.exists()) {
            throw new IllegalArgumentException("This file does not exist");
        }
    }

    public static void writeAll(String path, List<String> lines) throws IOException {
        if (Objects.isNull(path)) {
            throw new IllegalArgumentException("Path should not be null");
        } else if (lines == null) {
            throw new IllegalArgumentException("List should not be null");
        }
        File file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException("This might be a directory");
        } else {
            try (FileWriter fileWriter = new FileWriter(path)) {
                for (String line : lines) {
                    fileWriter.write(line + System.lineSeparator());
                }
            } catch (IOException e) {
                throw new IOException("Error while writing to file",e);
            }
        }
    }

    public static List<String> readAll(String path) throws IOException {
        if (Objects.isNull(path)) {
            throw new IllegalArgumentException("Path should not be null");
        }
        File file = new File(path);
        if (!file.exists()){
            throw new FileNotFoundException("File does not exist");
        }
        else if (!file.isFile()) {
            throw new FileNotFoundException("This might be a directory");
        }
        List<String> lines = new ArrayList<>();
        try (FileReader fileReader = new FileReader(path)) {
            BufferedReader br = new BufferedReader(fileReader);
            String s;
            while ((s = br.readLine()) != null) {
                lines.add(s);
            }
        } catch (IOException e) {
            throw new IOException("Error while reading from file" ,e);
        }
        return lines;
    }


}