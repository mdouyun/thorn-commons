/*
 * Copyright (c) 1994, 2010, JDJR and/or its affiliates. All rights reserved.
 *
 */
package org.thorn.code.common.protocol;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Http接口请求对象
 *
 * @author chenyun by 2020-07-14 14:14
 * @since 1.0
 */
@Data
@ToString(callSuper = true)
public class WebRequest<T> extends ApiRequest<T> {

    /**
     * http接口通用参数
     */
    private Map<String, String> httpCommons = new HashMap<String, String>();

}
