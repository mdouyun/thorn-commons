/*
 * Copyright (c) 1994, 2010, JDJR and/or its affiliates. All rights reserved.
 *
 */
package org.thorn.code.common.protocol;

import lombok.Data;
import lombok.ToString;
import org.thorn.code.common.constans.ResponseCode;

/**
 * http接口响应对象.
 *
 * @author chenyun by 2020-07-14 14:14
 * @since 1.0
 */
@Data
@ToString(callSuper = true)
public class WebResponse<T> extends BaseResponse<T> {

    public WebResponse(String code, String msg, T data) {
        super(code, msg, data);
    }

    /**
     * 返回成功
     *
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> ofSuccess() {
        return new WebResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), null);
    }

    /**
     * 返回带数据的成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> ofSuccess(T data) {
        return new WebResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    /**
     * 返回失败
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> ofFailure(String code, String msg) {
        return new WebResponse<T>(code, msg, null);
    }

    /**
     * 返回失败错误码及错误详细数据对象
     *
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> ofFailure(ResponseCode code, T data) {
        return new WebResponse<T>(code.getCode(), code.getDesc(), data);
    }

    /**
     * 返回失败错误码
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> ofFailure(ResponseCode code) {
        return new WebResponse<T>(code.getCode(), code.getDesc(), null);
    }
}
