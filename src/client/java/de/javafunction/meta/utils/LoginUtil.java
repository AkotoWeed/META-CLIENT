package de.javafunction.meta.utils;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class LoginUtil {


    public static void login() throws LoginException {
        String hwid = HWIDGenerator.getHWID();
        if (Objects.equals(hwid, "6990B67885ACAC27A86DA19BC42802E0E2DBDEE3AC5B36F0576ED287B1E144F0") || Objects.equals(hwid, "D055708B79532F08B137AB6C724C9BC39BB40265511C294DA90BEDB0DCA06A4E8C6DA77CB268C7467A54AF8437844B39803FD31503CC55D0FAE750E674F8EEF0")) {
        } else {
            System.exit(133712345);
            }
        }
    }
