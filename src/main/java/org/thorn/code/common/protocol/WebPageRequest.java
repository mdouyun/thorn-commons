/*
 * Copyright (c) 1994, 2010, JDJR and/or its affiliates. All rights reserved.
 *
 */
package org.thorn.code.common.protocol;

import lombok.Data;
import lombok.ToString;

/**
 * http接口分页请求对象.
 *
 * @author chenyun by 2020-07-14 17:36
 * @since 1.0
 */
@Data
@ToString(callSuper = true)
public class WebPageRequest<T> extends WebRequest<T> {

    /**
     * 分页的请求参数，起始页从1开始
     */
    private int pageNum = 1;

    /**
     * 每页大小.
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 是否为升序 ASC（ 默认： true ）
     *
     */
    private boolean asc = true;
}
