package com.gzdata.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.codec.Base64;

public class MD5Util {
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	/**
     * 计算字符串的hash值
     * @param string    明文
     * @param algorithm 算法名
     * @return          字符串的hash值
     */
    public static String hash(String string, String algorithm) throws Exception {
        if (string.isEmpty()) {
            return "";
        }
        MessageDigest hash = null;
        
        hash = MessageDigest.getInstance(algorithm);
        byte[] bytes = hash.digest(string.getBytes("UTF-8"));
        String result = "";
        for (byte b : bytes) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            result += temp;
        }
        return result;
    }
    
    
    /**
     * base加密
     * @param input
     * @return
     */
    public static String encodeBase64(String input) {
        return Base64.encodeToString(input.getBytes());
    }

    /**
     * base64解密
     * @param input
     * @return
     */
    public static String decodeBase64String(String input) {
        return Base64.decodeToString(input.getBytes());
    }
    
}