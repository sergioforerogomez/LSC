package com.lsc.users.Utils;

import java.util.UUID;

public class Utils {
    public static String getRandomEmail() {
        return "email-" + UUID.randomUUID().toString().substring(0, 8) + "@domain.com";
    }

    public static String getRandomName() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static String getRandomPassword() {
        return "A" + UUID.randomUUID().toString().substring(0, 6) + "0";
    }
}
