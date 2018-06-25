/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thorn.code.common.security;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;

/**
 * DES算法加解密类.
 *
 * @author chenyun.chris
 * @since 1.0
 */
public class DES {

    private static final Logger LOGGER = LoggerFactory.getLogger(DES.class);

    /**
     * 默认加密模式：ECB（电子密码本）
     */
    private static final String DEFAULT_MODE = "ECB";

    /**
     * 默认对称加密填充算法
     */
    private static final String DEFAULT_SYM_PADDING = "PKCS5Padding";

    private static final String DES_ALGORITHM = String.format("DES/%s/%s", DEFAULT_MODE, DEFAULT_SYM_PADDING);

    private static final String TRIPLE_DES_ALGORITHM = String.format("DES/%s/%s", DEFAULT_MODE, DEFAULT_SYM_PADDING);


    /**
     * DES算法对字节数组进行加密，使用模式为ECB，padding方式为PKCS5Padding。
     *
     * @param data
     * @param key
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] encrypt(byte[] data, String key) throws GeneralSecurityException {
        byte[] desKey = new DESKeySpec(StringUtils.getBytesUtf8(key)).getKey();
        SecretKey secretKey = new SecretKeySpec(desKey, "DES");

        return cipher(data, secretKey, Cipher.ENCRYPT_MODE, DES_ALGORITHM);
    }

    /**
     * DES算法对字节数组进行解密，使用模式为ECB，padding方式为PKCS5Padding。
     *
     * @param data
     * @param key
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] decrypt(byte[] data, String key) throws GeneralSecurityException {
        byte[] desKey = new DESKeySpec(StringUtils.getBytesUtf8(key)).getKey();
        SecretKey secretKey = new SecretKeySpec(desKey, "DES");

        return cipher(data, secretKey, Cipher.DECRYPT_MODE, DES_ALGORITHM);
    }


    /**
     * DES算法对字符串加密，使用模式为ECB，padding方式为PKCS5Padding。<br/>
     * 对加密的字节数组做Base64编码。
     *
     * @param data  需要加密的字符串
     * @param key  密钥
     * @return  加密后的Base64字符串
     * @throws GeneralSecurityException
     */
    public static String encrypt(String data, String key) throws GeneralSecurityException {
        // 明文作base64
        byte[] bytes = StringUtils.getBytesUtf8(data);

        byte[] desKey = new DESKeySpec(StringUtils.getBytesUtf8(key)).getKey();
        SecretKey secretKey = new SecretKeySpec(desKey, "DES");

        byte[] encryptBytes = cipher(bytes, secretKey, Cipher.ENCRYPT_MODE, DES_ALGORITHM);

        return org.apache.commons.codec.binary.Base64.encodeBase64String(encryptBytes);
    }

    /**
     * DES算法对字符串解密，使用模式为ECB，padding方式为PKCS5Padding。<br/>
     * 需要解密的字符串必须是Base64编码后的字符串。
     *
     * @param data  需要解密的Base64字符串
     * @param key  密钥
     * @return
     * @throws GeneralSecurityException
     */
    public static String decrypt(String data, String key) throws GeneralSecurityException {
        byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(data);

        byte[] desKey = new DESKeySpec(StringUtils.getBytesUtf8(key)).getKey();
        SecretKey secretKey = new SecretKeySpec(desKey, "DES");

        byte[] decryptBytes = cipher(bytes, secretKey, Cipher.DECRYPT_MODE, DES_ALGORITHM);

        return StringUtils.newStringUtf8(decryptBytes);
    }


    /**
     * 3DES算法对字节数组进行加密，使用模式为ECB，padding方式为PKCS5Padding。
     *
     * @param data
     * @param key
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] encryptByTriple(byte[] data, String key) throws GeneralSecurityException {
        byte[] desKey = new DESKeySpec(StringUtils.getBytesUtf8(key)).getKey();
        SecretKey secretKey = new SecretKeySpec(desKey, "DESede");

        return cipher(data, secretKey, Cipher.ENCRYPT_MODE, TRIPLE_DES_ALGORITHM);
    }

    /**
     * 3DES算法对字节数组进行解密，使用模式为ECB，padding方式为PKCS5Padding。
     *
     * @param data
     * @param key
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] decryptByTriple(byte[] data, String key) throws GeneralSecurityException {
        byte[] desKey = new DESKeySpec(StringUtils.getBytesUtf8(key)).getKey();
        SecretKey secretKey = new SecretKeySpec(desKey, "DESede");

        return cipher(data, secretKey, Cipher.DECRYPT_MODE, TRIPLE_DES_ALGORITHM);
    }


    /**
     * 3DES算法对字符串加密，使用模式为ECB，padding方式为PKCS5Padding。<br/>
     * 对加密的字节数组做Base64编码。
     *
     * @param data  需要加密的字符串
     * @param key  密钥
     * @return  加密后的Base64字符串
     * @throws GeneralSecurityException
     */
    public static String encryptByTriple(String data, String key) throws GeneralSecurityException {
        // 明文作base64
        byte[] bytes = StringUtils.getBytesUtf8(data);

        byte[] desKey = new DESKeySpec(StringUtils.getBytesUtf8(key)).getKey();
        SecretKey secretKey = new SecretKeySpec(desKey, "DESede");

        byte[] encryptBytes = cipher(bytes, secretKey, Cipher.ENCRYPT_MODE, TRIPLE_DES_ALGORITHM);

        return org.apache.commons.codec.binary.Base64.encodeBase64String(encryptBytes);
    }

    /**
     * 3DES算法对字符串解密，使用模式为ECB，padding方式为PKCS5Padding。<br/>
     * 需要解密的字符串必须是Base64编码后的字符串。
     *
     * @param data  需要解密的Base64字符串
     * @param key  密钥
     * @return
     * @throws GeneralSecurityException
     */
    public static String decryptByTriple(String data, String key) throws GeneralSecurityException {
        byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(data);

        byte[] desKey = new DESKeySpec(StringUtils.getBytesUtf8(key)).getKey();
        SecretKey secretKey = new SecretKeySpec(desKey, "DESede");

        byte[] decryptBytes = cipher(bytes, secretKey, Cipher.DECRYPT_MODE, TRIPLE_DES_ALGORITHM);

        return StringUtils.newStringUtf8(decryptBytes);
    }


    /**
     * 通用加解密方式。
     *
     * @param data  原文
     * @param key  密钥
     * @param opMode  模式（ENCODE_MODE|DECRYPT_MODE）
     * @param alg  算法：算法/模式/填充算法，如DES/ECB/PKCS5Padding
     * @return  加密/解密结果。字节数组形式
     * @throws GeneralSecurityException  加解密出现错误时抛出
     * @throws IllegalArgumentException  参数不合法时抛出
     */
    private static byte[] cipher(byte[] data, Key key, int opMode, String alg)
            throws GeneralSecurityException, IllegalArgumentException {
        long start = System.nanoTime();

        Cipher c1 = Cipher.getInstance(alg);
        c1.init(opMode, key);
        byte[] result = c1.doFinal(data);

        long used = System.nanoTime() - start;
        if (LOGGER.isDebugEnabled()) {//
            LOGGER.debug("#cipher {} {} used {} nano.",
                    opMode == Cipher.ENCRYPT_MODE ? "encode" : "decode", alg, used);
        }

        return result;
    }


}
