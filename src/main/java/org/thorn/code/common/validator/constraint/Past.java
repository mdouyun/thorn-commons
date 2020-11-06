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
import org.thorn.code.common.validator.Constraint;
import org.thorn.code.common.exception.ValidationException;

import java.util.Calendar;
import java.util.Date;

/**
 * 支持Date、Calendar，判断时间是否为当前时间之前.
 *
 * @author chenyun.chris
 * @since 2018.05.10
 */
public class Past implements Constraint {

    private static final Logger LOGGER = LoggerFactory.getLogger(Past.class);

    private final String message = "must be in the past";

    @Override
    public void check(Object value, String... checkArgs) throws ValidationException {
        LOGGER.debug("value:" , value , ",args:" , StringUtils.join(checkArgs));

        if(value == null || (!(value instanceof Date) && !(value instanceof Calendar))) {
            LOGGER.warn("Past constraint input may not be right!");
            return;
        }

        if(value instanceof Date) {
            Date n = new Date();

            if(((Date) value).compareTo(n) >= 0) {
                throw new ValidationException(this.message);
            }

        } else if(value instanceof Calendar) {
            Calendar n = Calendar.getInstance();

            if(((Calendar) value).compareTo(n) >= 0) {
                throw new ValidationException(this.message);
            }
        }


    }
}
