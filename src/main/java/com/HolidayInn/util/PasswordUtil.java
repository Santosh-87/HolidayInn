package com.HolidayInn.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for encrypting and decrypting passwords using AES with GCM.
 *
 * It generates a secret key from the username and uses it to safely encrypt 
 * and decrypt passwords. Useful for storing sensitive data securely.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */

public class PasswordUtil {
	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

    private static final int TAG_LENGTH_BIT = 128; // must be one of {128, 120, 112, 104, 96}
    private static final int IV_LENGTH_BYTE = 12;
    private static final int SALT_LENGTH_BYTE = 16;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * Generates a cryptographically secure random nonce (number used once).
     *
     * @param numBytes the length of the nonce in bytes
     * @return a byte array containing the random nonce
     */
    public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    /**
     * Generates a new AES secret key of specified size.
     *
     * @param keysize the key size in bits (128, 192, or 256)
     * @return the generated SecretKey
     * @throws NoSuchAlgorithmException if the AES algorithm is not available
     */
    public static SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keysize, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }

    /**
     * Derives an AES secret key from a password using PBKDF2 with SHA-256.
     *
     * @param password the password to derive the key from
     * @param salt the random salt to use for key derivation
     * @return the derived SecretKey, or null if derivation fails
     */
    public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt){
           	try {
           		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
           		// iterationCount = 65536
           		// keyLength = 256
           		KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
           		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
           		return secret;
       		} catch (NoSuchAlgorithmException ex) {
       			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
           	} catch (InvalidKeySpecException ex) {
           		Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
           	}
       		return null;
    }

    /**
     * Encrypts a password using AES-GCM with a username-derived key.
     *
     * @param username the username used for key derivation
     * @param password the password to encrypt
     * @return Base64-encoded encrypted string containing IV, salt and ciphertext,
     *         or null if encryption fails
     */
    public static String encrypt(String username, String password){
    	try {
		    // 16 bytes salt
		    byte[] salt = getRandomNonce(SALT_LENGTH_BYTE);
		
		    // GCM recommended 12 bytes iv?
		    byte[] iv = getRandomNonce(IV_LENGTH_BYTE);
		
		    // secret key from password
		    SecretKey aesKeyFromPassword = getAESKeyFromPassword(username.toCharArray(), salt);
		
		    Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
		
		    // ASE-GCM needs GCMParameterSpec
		    cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
		
		    byte[] cipherText = cipher.doFinal(password.getBytes());
		
		    // prefix IV and Salt to cipher text
		    byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
		            .put(iv)
		            .put(salt)
		            .put(cipherText)
		            .array();
		
		    // string representation, base64, send this string to other for decryption.
		    return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
    	}catch(Exception ex) {
    		return null;
    	}

    }

    /**
     * Decrypts an encrypted password using a username-derived key.
     *
     * @param encryptedPassword the Base64-encoded encrypted string (IV+salt+ciphertext)
     * @param username the username used for key derivation
     * @return the decrypted password as plaintext, or null if decryption fails
     */
    public static String decrypt(String encryptedPassword, String username) {
		try {
			byte[] decode = Base64.getDecoder().decode(encryptedPassword.getBytes(UTF_8));
	
			// get back the iv and salt from the cipher text
			ByteBuffer bb = ByteBuffer.wrap(decode);
	
			byte[] iv = new byte[IV_LENGTH_BYTE];
			bb.get(iv);
	
			byte[] salt = new byte[SALT_LENGTH_BYTE];
			bb.get(salt);
	
			byte[] cipherText = new byte[bb.remaining()];
			bb.get(cipherText);
	
			// get back the aes key from the same password and salt
			SecretKey aesKeyFromPassword = PasswordUtil.getAESKeyFromPassword(username.toCharArray(), salt);
	
			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
	
			cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
	
			byte[] plainText = cipher.doFinal(cipherText);
		
			return new String(plainText, UTF_8);
		}catch(Exception ex) {
			return null;
		}
	}
}