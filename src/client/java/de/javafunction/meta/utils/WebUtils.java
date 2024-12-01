package de.javafunction.meta.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebUtils {
    public static void main(String[] args) {
        String urlString = "http://durchlasten.world/meta/hwid.java";
        String javaCode = readJavaCodeFromURL(urlString);

        System.out.println("Java Code von der URL:");
        System.out.println(javaCode);
    }

    public static String readJavaCodeFromURL(String urlString) {
        StringBuilder javaCodeBuilder = new StringBuilder();

        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                javaCodeBuilder.append(line).append("\n");
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaCodeBuilder.toString();
    }
}
