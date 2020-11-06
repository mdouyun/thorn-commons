/*
 * Copyright (c) 1994, 2010, JDJR and/or its affiliates. All rights reserved.
 *
 */
package org.thorn.code.common.constans;

/**
 * 响应码枚举类.
 *
 * @author chenyun by 2020-10-31 18:44
 * @since 1.0
 */
public enum ResponseCode {

    SUCCESS("00000", "成功"),
    SYSTEM_ERROR("SY999", "系统异常"),
    VALIDA_ERROR("SY101", "参数校验未通过"),
    PARAM_ERROR("SY100", "参数错误"),

    ;

    private String code;
    private String desc;

    ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResponseCode getEnum(String code) {
        ResponseCode[] enums = ResponseCode.values();
        for (ResponseCode $enum : enums) {
            if ($enum.getCode().equals(code)) {
                return $enum;
            }
        }
        return null;
    }

    public static String getDescByCode(String code) {
        ResponseCode $enum = getEnum(code);
        return $enum == null ? "" : $enum.getDesc();
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseCode{");
        sb.append("code='").append(code).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
