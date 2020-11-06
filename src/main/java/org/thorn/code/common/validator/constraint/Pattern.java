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

import java.util.regex.Matcher;

/**
 * 支持String, 正则相关参数参考 {@link java.util.regex.Pattern}.
 *
 * @author chenyun.chris
 * @since 2018.05.10
 */
public class Pattern implements Constraint {

    private static final Logger LOGGER = LoggerFactory.getLogger(Pattern.class);

    private final String message = "must match \"{regexp}\"";

    @Override
    public void check(Object value, String... checkArgs) throws ValidationException {
        LOGGER.debug("value: {}" , value , ",args:" , StringUtils.join(checkArgs));

        if(value == null || !(value instanceof String) || checkArgs.length != 2) {
            LOGGER.warn("Pattern constraint args may not right!");
            return;
        }

        String regex = checkArgs[0];
        int flag = Integer.parseInt(checkArgs[1]);

        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex, flag);

        Matcher m = p.matcher((String)value);
        if(!m.matches()) {
            throw new ValidationException(this.message);
        }

    }
}
