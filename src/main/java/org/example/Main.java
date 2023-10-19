package org.example;

public class Main {
    public static void main(String[] args){
        try{
            AESEncryptionImpl aesEncryption = new AESEncryptionImpl();
            aesEncryption.init();
            String excrptedText = aesEncryption.encrypt(" Payment Received");
            String decryptedText = aesEncryption.decrypt(excrptedText);

            System.out.println("The encrypted Data in string: " + excrptedText);
            System.out.println("The decrypted Data in string:  " + decryptedText);
        } catch(Exception ignored){

        }
    }
}