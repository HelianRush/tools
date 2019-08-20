package com.fool.sys_tool;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.List;
import java.util.Random;

/**
 * 盐值
 */
public class SaltTools {

    /**
     * 加密算法
     **/
    public final static String HASH_ALGORITHM_MD5 = "MD5";
    public final static String HASH_ALGORITHM_SHA256 = "SHA-256";
    /**
     * 循环次数
     **/
    public final static int HASH_ITERATIONS = 15;

    /**
     * 盐值生成
     */
    public static String CreateSalt() {
        StringBuffer random16 = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            random16.append(Integer.toHexString(random.nextInt(16)));
        }
        return random16.toString().toUpperCase();
    }

    /**
     * 加密 MD5
     */
    public static String EncryptMd5(String password, String salt) {
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        SimpleHash hash = new SimpleHash(HASH_ALGORITHM_MD5, password, byteSource, HASH_ITERATIONS);
        return hash.toString();
    }

    /**
     * 加密 SHA256
     */
    public static String EncryptSha256(String password, String salt) {
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        SimpleHash hash = new SimpleHash(HASH_ALGORITHM_SHA256, password, byteSource, HASH_ITERATIONS);
        return hash.toString();
    }

}
