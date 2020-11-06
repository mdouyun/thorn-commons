/*
 * Copyright (c) 1994, 2010, JDJR and/or its affiliates. All rights reserved.
 *
 */
package org.thorn.code.common.protocol;

import lombok.Data;
import lombok.ToString;
import org.thorn.code.common.constans.ResponseCode;

import java.util.List;

/**
 * http接口分页响应对象.
 *
 * @author chenyun by 2020-07-14 17:36
 * @since 1.0
 */
@Data
@ToString(callSuper = true)
public class WebPageResponse<T> extends BaseResponse<List<T>> {

    private static final long serialVersionUID = 1L;

    /**
     * 总条数
     */
    private long total;

    /**
     * page size
     * <p>每页显示条数，默认 10</p>
     */
    private int pageSize = 10;

    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     *
     * @param code
     * @param msg
     * @param data
     * @param total
     */
    public WebPageResponse(String code, String msg, List<T> data, long total) {
        super(code, msg, data);
        this.total = total;
    }

    /**
     * 返回带数据的成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> WebPageResponse<T> ofSuccess(List<T> data, long total) {
        return new WebPageResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data, total);
    }

    /**
     * 返回带数据的成功
     *
     * @param code
     * @param data
     * @param total
     * @param <T>
     * @return
     */
    public static <T> WebPageResponse<T> ofSuccess(ResponseCode code, List<T> data, long total) {
        return new WebPageResponse<T>(code.getCode(), code.getDesc(), data, total);
    }

    /**
     * 返回失败
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> WebPageResponse<T> ofFailure(String code, String msg) {
        return new WebPageResponse<T>(code, msg, null, 0);
    }

    /**
     * 返回失败错误码
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> WebPageResponse<T> ofFailure(ResponseCode code) {
        return new WebPageResponse<T>(code.getCode(), code.getDesc(), null, 0);
    }
}
