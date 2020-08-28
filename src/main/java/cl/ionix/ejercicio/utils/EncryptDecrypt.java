package cl.ionix.ejercicio.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;


public class EncryptDecrypt {

    public static void main(String[] args) throws Exception {
        String key = "ionix123456";

        String ciphertext = encrypt(key, "1-9");

        String decrypted = decrypt(key, ciphertext.trim());
        String encrypted = encrypt(key, decrypted.trim());

        System.out.println("encrypted: "+encrypted);
        System.out.println("decrypted: "+decrypted);

        if (ciphertext.contentEquals(encrypted.trim())) {
            System.out.println("desencriptado");
        } else {
            System.out.println("Llave!");
        }
    }

    public static String encrypt(String key, String data)
            throws GeneralSecurityException {
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(dataBytes));
    }

    public static String decrypt(String key, String data)
            throws GeneralSecurityException {
        byte[] dataBytes = Base64.getDecoder().decode(data);
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] dataBytesDecrypted = (cipher.doFinal(dataBytes));
        return new String(dataBytesDecrypted);
    }
}

