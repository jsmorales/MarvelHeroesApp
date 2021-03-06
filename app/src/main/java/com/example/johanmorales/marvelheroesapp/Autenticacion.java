package com.example.johanmorales.marvelheroesapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Autenticacion {

    public final static String URL_BASE = "https://gateway.marvel.com/";
    public final static String PRIVATE_KEY = "9ec15e6d9761c839566c5cd164891a94dc05a753";
    public final static String PUBLIC_KEY = "6062af1679edc54bc4ae69791d995528";
    public final static String TS = "1";


    public static String getHash() {

        StringBuilder hexString = new StringBuilder();

        try {

            String unconvertedHash = TS+PRIVATE_KEY+PUBLIC_KEY;

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(unconvertedHash.getBytes());

            byte messageDigest[] = md.digest();

            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexString.toString();
    }

    public static String convertHash(String unconvertedHash) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(unconvertedHash.getBytes());

        byte messageDigest[] = md.digest();

        StringBuilder hexString = new StringBuilder();

        for (byte aMessageDigest : messageDigest) {
            String h = Integer.toHexString(0xFF & aMessageDigest);
            while (h.length() < 2)
                h = "0" + h;
            hexString.append(h);
        }

        return hexString.toString();
    }
}
