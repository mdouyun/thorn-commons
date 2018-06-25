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
package org.thorn.code.common.validator;

import org.apache.commons.lang3.StringUtils;

/**
 * 提示消息格式化类.
 *
 * @author chenyun.chris
 * @since 2018.05.14
 */
public class MessageFormat {

    /**
     * 对约束的错误提示进行格式化.
     *
     * @param error 约束类的错误提示消息
     * @param name 属性名称，带路径
     * @param format 消息模版，注解中定义
     * @return
     */
    String format(String error, String name, String format) {
        error = StringUtils.replace(format, "{error}", error);
        error = StringUtils.replace(error, "{name}", name);
        return error;
    }

    String getRootNullMessage() {
        return "The root object is null!";
    }

}
