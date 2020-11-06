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

package org.thorn.code.common.exception;

import org.thorn.code.common.constans.ResponseCode;

/**
 * 校验异常类，具体约束类校验不通过则抛出异常.
 *
 * @author chenyun.chris
 * @since 2018.05.09
 */
public class ValidationException extends BizException {

    public ValidationException(String message) {
        super(ResponseCode.VALIDA_ERROR.getCode(), message);
    }

    public ValidationException(ResponseCode code) {
        super(code);
    }

    public ValidationException(String code, String message) {
        super(code, message);
    }

    public ValidationException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ValidationException(ResponseCode code, Throwable cause) {
        super(code, cause);
    }
}
