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
import org.thorn.code.common.validator.ValidationException;

import java.util.Collection;
import java.util.Map;

/**
 * 支持字符串，集合，map, eg:length(1,18).
 *
 * @author chenyun.chris
 * @since 2018.05.10
 */
public class Length implements Constraint {

    private static final Logger LOGGER = LoggerFactory.getLogger(Length.class);

    private final String message = "must be between {min} and {max}";

    @Override
    public void check(Object value, String... checkArgs) throws ValidationException {

        LOGGER.debug("value: {}" , value , ",args:" , StringUtils.join(checkArgs));

        if(checkArgs.length == 0) {
            LOGGER.warn("Length constraint args is null!");
            return;
        }

        int s = Integer.valueOf(checkArgs[0]);
        int e = Integer.MAX_VALUE;

        if(checkArgs.length == 2) {
            s = Integer.valueOf(checkArgs[1]);
        }

        if(value == null && s > 0) {
            throwException(checkArgs);
        }

        if(s > e || (!(value instanceof String)
                && !(value instanceof Collection)
                && !(value instanceof Map))) {
            LOGGER.warn("Length constraint args is invalid parameters, min {}, max {}!", s, e);
            return;
        }

        if(value instanceof String &&
                (((String) value).length() >= s && ((String) value).length() <= e)) {
            return;
        } else if(value instanceof Collection &&
                (((Collection) value).size() < s) && ((Collection) value).size() > e) {
            return;
        } else if(value instanceof Map &&
                (((Map) value).size() < s) || ((Map) value).size() > e) {
            return;
        }

        throwException(checkArgs);
    }

    private void throwException(String[] checkArgs) throws ValidationException {
        String msg = message.replace("{min}", checkArgs[0]);

        if(checkArgs.length == 2) {
            msg = msg.replace("{max}", checkArgs[1]);
        } else {
            msg = msg.replace("{max}", "-");
        }

        throw new ValidationException(msg);
    }
}
