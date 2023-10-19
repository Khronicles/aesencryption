package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Logger;

public class AESEncryptionImpl {
    Logger log = Logger.getLogger(AESEncryptionImpl.class.getName());
    private SecretKey key;
    private final int KEY_SIZE = 128;
    private final int DATA_LENGTH = 128;
    private Cipher encryptionCipher;

    // Generate the Secret key to use for encryption
    // AES-128, AES-192, AES-256
    public void init() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();
        log.info("This is the secret key: " + key);
    }

    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    // I will be right back.....
    public String encrypt(String data) throws Exception {
        byte[] dataInBytes = data.getBytes();
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
        log.info("The encrypted data in bytes: " + Arrays.toString(encryptedBytes));
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception{
        byte[] dataByte = decode(encryptedData);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/Nopadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec);
        byte[] decryptedBytes = decryptionCipher.doFinal(dataByte);
        log.info("The decrypted data in bytes: " + Arrays.toString(decryptedBytes));
        return new String(decryptedBytes);
    }
}
