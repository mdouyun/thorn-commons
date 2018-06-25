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

/**
 * 常用的摘要算法类，MD5、SHA-256、SHA-1.
 *
 * @author chenyun.chris
 * @since 1.0
 */
public class DigestUtils extends org.apache.commons.codec.digest.DigestUtils {

    /**
     * 将数据加盐之后，对数据作MD5摘要算法。<br/>
     * 盐值算法：
     * @see #hexSalt(String, String)
     *
     * @param data  字符串数据
     * @param salt  数据盐
     * @return  摘要字符串
     */
    public static String md5Hex(String data, String salt) {
        return DigestUtils.md5Hex(hexSalt(data, salt));
    }

    /**
     * 将数据加盐之后，对数据作SHA-256摘要算法。<br/>
     * 盐值算法：
     * @see #hexSalt(String, String)
     *
     * @param data  字符串数据
     * @param salt  数据盐
     * @return  摘要字符串
     */
    public static String sha256Hex(String data, String salt) {
        return DigestUtils.sha256Hex(hexSalt(data, salt));
    }

    /**
     * 将数据加盐之后，对数据作SHA-1摘要算法。<br/>
     * 盐值算法：
     * @see #hexSalt(String, String)
     *
     * @param data  字符串数据
     * @param salt  数据盐
     * @return  摘要字符串
     */
    public static String sha1Hex(String data, String salt) {
        return DigestUtils.sha1Hex(hexSalt(data, salt));
    }

    /**
     * 数据加盐算法，字符串通过“+”相连。
     *
     * @param data  原数据
     * @param salt  数据盐
     * @return  混合后的data串
     */
    private static String hexSalt(String data, String salt) {
        StringBuilder hex = new StringBuilder(data);
        hex.append("+").append(salt);
        return hex.toString();
    }

}
