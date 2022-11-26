package com.martincastroalvarez.london; 

import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;

public class HashUtil {
    // --------------------------------------------------------------------
    // Class with utilities for hashing passwords.
    //
    // Hashing turns your password (or any other piece of data) into a
    // short string of letters and/or numbers using an encryption algorithm.
    // If a website is hacked, cyber criminals don't get access to your
    // password. Instead, they just get access to the encrypted “hash”
    // created by your password.
    //
    // References:
    // - https://stackoverflow.com/questions/2860943
    // --------------------------------------------------------------------

    @Autowired
    private GlobalProperties config;

    private SecureRandom random;
    private Base64.Encoder encoder;
    private SecretKeyFactory factory;

    public void HashUtil() throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.random = new SecureRandom();
        this.encoder = Base64.getUrlEncoder().withoutPadding();
        this.factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    }

    public String hash(String password) throws InvalidKeySpecException {
        // --------------------------------------------------------------------
        // Hashing with the `PBKDF2WithHmacSHA1` algorithm.
        // --------------------------------------------------------------------
        byte[] salt = new byte[16];
        random.nextBytes(config.getSalt().getBytes());
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return encoder.encodeToString(hash);
    }

}
