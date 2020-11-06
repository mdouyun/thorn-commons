/*
 * Copyright (c) 1994, 2010, JDJR and/or its affiliates. All rights reserved.
 *
 */
package org.thorn.code.common.protocol;

import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础请求对象，定义了最基础的字段，不可用直接使用.
 *
 * @author chenyun by 2020-07-14 15:17
 * @since 1.0
 */
@Data
public abstract class BaseRequest<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String version;

    /**
     * 业务对象
     */
    @Valid
    private T data;

    /**
     * 附加数据
     */
    private Map<String, Object> ext = new HashMap<String, Object>();
}
