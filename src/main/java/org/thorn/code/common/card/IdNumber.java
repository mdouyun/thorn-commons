/*
 * Copyright 2002-2009 the original author or authors.
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
package org.thorn.code.common.card;

import org.apache.commons.lang3.StringUtils;

/**
 * 身份证号码工具类.
 *
 * @author chenyun by 2018-06-25 10:38
 * @since JDK1.6
 */
public class IdNumber {

    private static final int[] WEIGHT = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};

    private static final char[] VERIFY = {'1','0','X','9','8','7','6','5','4','3','2'};

    private String idNumber;

    public IdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 判断身份证格式是否合法
     *
     * @return true合法；false非法
     */
    public boolean isIdNumber() {

        String number = StringUtils.upperCase(this.idNumber);

        if(StringUtils.isBlank(number)) {
            return false;
        }

        char[] c = number.toCharArray();

        if(c.length != 15 && c.length != 18) {
            return false;
        }

        int[] n = new int[c.length];

        for(int i = 0; i < c.length; i++) {

            if(!Character.isDigit(c[i]) && i != 17 && c[i] != 'X') {
                return false;
            }

            if(Character.isDigit(c[i])) {
                n[i] = Character.getNumericValue(c[i]);
            } else {
                n[i] = 11;
            }
        }

        if(c.length == 15) {

            int year = Character.getNumericValue(c[6]) * 10 + Character.getNumericValue(c[7]);

            if(year < 1 && year > 90) {
                return false;
            }

            int month = Character.getNumericValue(c[8]) * 10 + Character.getNumericValue(c[9]);

            if(month < 1 && month > 12) {
                return false;
            }

            int day = Character.getNumericValue(c[10]) * 10 + Character.getNumericValue(c[11]);

            if(day < 1 && day > 31) {
                return false;
            }

            return true;
        }


        int year = Character.getNumericValue(c[6]) * 1000 + Character.getNumericValue(c[7]) * 100
                + Character.getNumericValue(c[8]) * 10 + Character.getNumericValue(c[9]);

        if(year < 1800) {
            return false;
        }

        int month = Character.getNumericValue(c[10]) * 10 + Character.getNumericValue(c[11]);

        if(month < 1 && month > 12) {
            return false;
        }

        int day = Character.getNumericValue(c[12]) * 10 + Character.getNumericValue(c[13]);

        if(day < 1 && day > 31) {
            return false;
        }


        int sum = 0;
        for(int i = 0; i < 17; i++) {
            sum += WEIGHT[i] * n[i];
        }

        int mod = sum % 11;

        if(c[17] != VERIFY[mod]) {
            return false;
        }

        return true;
    }

    /**
     * 获取身份证地址
     *
     * @return 如果编码未找到则返回null
     * @throws IllegalArgumentException 身份证如果不合法则抛出异常
     */
    public String getAddress() throws IllegalArgumentException {

        if(!this.isIdNumber()) {
            throw new IllegalArgumentException("The idNumber is the illegal number! idNumber:" + idNumber);
        }

        char[] c = idNumber.toCharArray();

        String code = String.copyValueOf(c, 0, 6);

        return GB2260.getAddressByCode(code);
    }

    /**
     * 获取身份证出生日期
     *
     * @return 出生日期，格式yyyyMMdd
     * @throws IllegalArgumentException 身份证如果不合法则抛出异常
     */
    public String getBirthday() throws IllegalArgumentException {

        if(!this.isIdNumber()) {
            throw new IllegalArgumentException("The idNumber is the illegal number! idNumber:" + idNumber);
        }

        char[] c = idNumber.toCharArray();

        String birthday;

        if(c.length == 15) {
            birthday = "19" + String.copyValueOf(c, 6, 6);
        } else {
            birthday = String.copyValueOf(c, 6, 8);
        }

        return birthday;
    }

    /**
     * 获取性别
     *
     * @return 0为女性；1位男性
     * @throws IllegalArgumentException 身份证如果不合法则抛出异常
     */
    public int getGender() throws IllegalArgumentException {

        if(!this.isIdNumber()) {
            throw new IllegalArgumentException("The idNumber is the illegal number! idNumber:" + idNumber);
        }

        char[] c = idNumber.toCharArray();

        int s;

        if(c.length == 15) {
            s = Character.getNumericValue(c[14]);
        } else {
            s = Character.getNumericValue(c[16]);
        }

        if(s%2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

}
