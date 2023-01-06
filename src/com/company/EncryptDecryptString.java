package com.company;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class EncryptDecryptString {
    private static final String UNICODE_FORMAT;

    static {
        UNICODE_FORMAT = "UTF8";
    }

    public static void main(String[] args) {
        String text = "Hi";
        try {
            SecretKey key = generateKey("AES");
            Cipher chiper = Cipher.getInstance("AES");

            byte[] encryptedData = encryptString(text, key, chiper);
            String encryptedString = new String(encryptedData);
            System.out.println(encryptedString);

        } catch (Exception e) {
        }
    }

    public static void update(String text) {
        try {
            SecretKey key = generateKey("AES");
            Cipher chiper = Cipher.getInstance("AES");

            byte[] encryptedData = encryptString(text, key, chiper);
            String encryptedString = new String(encryptedData);
            System.out.println(encryptedString);

        } catch (Exception e) {
        }
    }

    public static SecretKey generateKey(String encryptionType) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType);
            return keyGenerator.generateKey();
        } catch (Exception exception) {
            return null;
        }
    }

    public static byte[] encryptString(String dataToEncrypt, SecretKey myKey, Cipher cipher) {
        try {
            byte[] text = dataToEncrypt.getBytes(UNICODE_FORMAT);
            cipher.init(Cipher.ENCRYPT_MODE, myKey);
            byte[] textEncrypted = cipher.doFinal(text);
            return textEncrypted;
        } catch (Exception exception) {
           return null;
        }
    }

}
