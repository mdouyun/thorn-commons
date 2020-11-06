/*
 * Copyright (c) 1994, 2010, JDJR and/or its affiliates. All rights reserved.
 *
 */
package org.thorn.code.common.protocol;

import lombok.Data;
import org.thorn.code.common.constans.ResponseCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 基础响应对象，定义了最基础的字段，不可用直接使用.
 *
 * @author chenyun by 2020-07-14 15:16
 * @since 1.0
 */
@Data
public abstract class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private String code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    private Map<String, Object> ext = new HashMap<String, Object>();


    public BaseResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 是否成功
     *
     * @return true 代表成功 false  代表失败
     */
    public boolean isSuccess() {
        return ResponseCode.SUCCESS.getCode().equals(this.code);
    }

    /**
     * 只要不成功，就算失败：拒绝访问也算失败
     *
     * @return
     */
    public boolean isFailed() {
        return !isSuccess();
    }


}
