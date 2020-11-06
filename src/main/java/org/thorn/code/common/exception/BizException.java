/*
 * Copyright (c) 1994, 2010, JDJR and/or its affiliates. All rights reserved.
 *
 */
package org.thorn.code.common.exception;

import lombok.Data;
import org.thorn.code.common.constans.ResponseCode;

/**
 * 业务异常
 *
 * @author chenyun by 2020-11-06 15:56
 * @since 1.0
 */
@Data
public class BizException extends Exception {

    private String code;

    private String message;

    public BizException(ResponseCode code) {
        super(code.getCode() + "#" + code.getDesc());
        this.code = code.getCode();
        this.message = code.getDesc();
    }

    public BizException(String code, String message) {
        super(code + "#" + message);
        this.code = code;
        this.message = message;
    }

    public BizException(String code, String message, Throwable cause) {
        super(code + "#" + message, cause);
        this.code = code;
        this.message = message;
    }

    public BizException(ResponseCode code, Throwable cause) {
        super(code.getCode() + "#" + code.getDesc(), cause);
        this.code = code.getCode();
        this.message = code.getDesc();
    }


}
