package de.javafunction.meta.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HWIDGenerator {

    public static String getHWID() {
        String computerName = System.getenv("COMPUTERNAME");
        String userName = System.getProperty("user.name");
        String processorIdentifier = System.getenv("PROCESSOR_IDENTIFIER");
        String processorLevel = System.getenv("PROCESSOR_LEVEL");

        String hwid = computerName + userName + processorIdentifier + processorLevel;

        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            byte[] md5HashBytes = md5Digest.digest(hwid.getBytes());

            StringBuilder md5HashBuilder = new StringBuilder();
            for (byte b : md5HashBytes) {
                md5HashBuilder.append(String.format("%02x", b));
            }
            String md5Hash = md5HashBuilder.toString();

            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-512");
            byte[] sha256HashBytes = sha256Digest.digest(md5Hash.getBytes());

            StringBuilder sha256HashBuilder = new StringBuilder();
            for (byte b : sha256HashBytes) {
                sha256HashBuilder.append(String.format("%02X", b));
            }
            String sha256Hash = sha256HashBuilder.toString();

            return sha256Hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String hwid = getHWID();
        System.out.println("HWID: " + hwid);
    }
}

