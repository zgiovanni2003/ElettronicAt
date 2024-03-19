package it.zgiovanni2003;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Password_Manager {
    private static final String ALGORITHM = "AES";
    private static SecretKey AES_KEY;
    

    private static SecretKey generateAESKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encrypt(String password) throws Exception {
    	AES_KEY = generateAESKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, AES_KEY);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public String encrypt(String password, String KEY) throws Exception {
    	byte[] keyBytes = Base64.getDecoder().decode(KEY);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    

    public String decrypt(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, AES_KEY);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }
    
    public String getKey(){
    	byte[] keyBytes = AES_KEY.getEncoded();
    	return Base64.getEncoder().encodeToString(keyBytes);
    }
}
