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
package org.thorn.code.common.validator.constraint;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thorn.code.commons.validator.Constraint;
import org.thorn.code.commons.validator.ValidationException;

import java.math.BigDecimal;

/**
 * 只支持数值类型的参数校验，eg:max(1000)
 *
 * @author chenyun.chris
 * @since 2018.05.10
 */
public class Max implements Constraint {

    private static final Logger LOGGER = LoggerFactory.getLogger(Max.class);

    private final String message = "must be less than or equal to {value}";

    @Override
    public void check(Object value, String... checkArgs) throws ValidationException {
        LOGGER.debug("value: {}" , value , ",args:" , StringUtils.join(checkArgs));

        if(value == null || checkArgs.length != 1) {
            LOGGER.warn("Max constraint args may not right!");
            return;
        }

        Comparable max;
        Comparable v;
        String type = value.getClass().getSimpleName();

        if(StringUtils.equals(type, "int") || value instanceof Integer) {
            max = Integer.valueOf(checkArgs[0]);
            v = (Integer) value;
        } else if(StringUtils.equals(type, "long") || value instanceof Long) {
            max = Long.valueOf(checkArgs[0]);
            v = (Long) value;
        } else if(StringUtils.equals(type, "double") || value instanceof Double) {
            max = Double.valueOf(checkArgs[0]);
            v = (Double) value;
        } else if(StringUtils.equals(type, "float") || value instanceof Float) {
            max = Float.valueOf(checkArgs[0]);
            v = (Float) value;
        } else if(StringUtils.equals(type, "short") || value instanceof Short) {
            max = Short.valueOf(checkArgs[0]);
            v = (Short) value;
        } else if(value instanceof BigDecimal) {
            max = new BigDecimal(checkArgs[0]);
            v = (BigDecimal) value;
        } else {
            LOGGER.warn("Max constraint input may not right!");
            return;
        }

        if(v.compareTo(max) > 0) {
            String msg = this.message.replace("{value}", checkArgs[0]);
            throw new ValidationException(msg);
        }
    }


}
