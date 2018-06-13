package com.lsc.dictionary.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    private static String searchInFile(String path, String word) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(word)) {
                    return line.replace(word, "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getAccessKeyFromFile(String path) {
        return searchInFile(path, "ACCESS_KEY:");
    }

    public static String getSecretKeyFromFile(String path) {
        return searchInFile(path, "SECRET_KEY:");
    }
}
