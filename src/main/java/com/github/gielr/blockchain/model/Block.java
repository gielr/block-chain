package com.github.gielr.blockchain.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Block {
    private String message;
    private String previousHash;
    private String hash;
    private Long timestamp;
    private String slower;

    public Block(String message, String previousHash) {
        this.message = message;
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    public void mine(int difficulty) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < difficulty; i++) {
            sb.append("0");
        }
        String requiredBegining = sb.toString();
        while (!hash.startsWith(requiredBegining)) {
            slower = UUID.randomUUID().toString();
            hash = calculateHash();
        }
    }

    private String calculateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((message + previousHash + timestamp + slower).getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(hash[i]);
                if (hex.length() == 1) {
                    sb.append(0);
                }
                sb.append(hex);
            }

            return sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public String getMessage() {
        return message;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }
}
