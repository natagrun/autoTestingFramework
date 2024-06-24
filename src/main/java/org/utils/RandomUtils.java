package org.utils;

public class RandomUtils {

    public static String generateRandomString() {
        int length = (int) (Math.random()*100);
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static int generateRandomInt() {
        return (int) (Math.random()*100);
    }



}
