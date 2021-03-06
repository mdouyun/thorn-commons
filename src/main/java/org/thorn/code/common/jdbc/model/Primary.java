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

package org.thorn.code.common.jdbc.model;

import org.thorn.code.common.jdbc.type.TypeHandler;
import org.thorn.code.common.jdbc.type.impl.DefaultTypeHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * javaBean属性对应的表主键名.
 *
 * @author chenyun.chris
 * @since 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Primary {

    /**
     *
     * @return 对应的列名，若返回""则返回类的属性名
     */
    String value() default "";

    /**
     *
     * @return
     */
    Class<? extends TypeHandler> type() default DefaultTypeHandler.class;

    /**
     *
     * @return 主键的生成策略
     */
    PKGeneratePolicy policy() default PKGeneratePolicy.VALUE;

    /**
     *
     * @return 通过sql生成时的sql语句
     */
    String sql() default "";

}
