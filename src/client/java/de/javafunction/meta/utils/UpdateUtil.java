package de.javafunction.meta.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UpdateUtil {
    public static void main(String[] args) {
        String url = "http://durchlasten.sidcool/meta/meta.jar";
        String desktopPath = System.getProperty("user.home") + "\\Desktop\\METAOUT\\meta.jar";
        downloadFile(url, desktopPath);
    }

    public static void downloadFile(String url, String savePath) {
        try {
            URL fileUrl = new URL(url);
            Path saveFilePath = Paths.get(savePath);

            if (!Files.exists(saveFilePath.getParent())) {
                Files.createDirectories(saveFilePath.getParent());
            }

            try (BufferedInputStream in = new BufferedInputStream(fileUrl.openStream());
                 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFilePath.toFile()))) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
