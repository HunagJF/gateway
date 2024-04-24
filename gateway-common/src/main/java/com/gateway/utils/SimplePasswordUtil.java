package com.gateway.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 */
public class SimplePasswordUtil {

    /**
     * 使用 SHA-256 对密码进行加密
     * @param passwordToHash 明文密码
     * @return 加密后的密码字符串
     */
    public static String encodePassword(String passwordToHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    passwordToHash.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法在当前环境中不可用", e);
        }
    }

    /**
     * 将字节转换为十六进制格式
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 对比明文密码和加密密码
     * @param rawPassword 明文密码
     * @param encryptedPassword 加密后的密码
     * @return 密码是否匹配
     */
    public static boolean checkPassword(String rawPassword, String encryptedPassword) {
        // 重新加密明文密码，然后和已加密的密码进行对比
        return encodePassword(rawPassword).equals(encryptedPassword);
    }
}
