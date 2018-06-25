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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 支持字符串.
 *
 * @author chenyun.chris
 * @since 2018.05.10
 */
public class Email implements Constraint {

    private static final Logger LOGGER = LoggerFactory.getLogger(Email.class);

    private static String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    private static String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)*";
    private static String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";
    private Pattern pattern;

    private final String message = "not a well-formed email address";

    public Email() {
        this.pattern = Pattern.compile("^" + ATOM + "+(\\." + ATOM + "+)*@" + DOMAIN + "|" + IP_DOMAIN + ")$", 2);
    }

    @Override
    public void check(Object value, String... checkArgs) throws ValidationException {
        LOGGER.debug("value:" , value , ",args:" , StringUtils.join(checkArgs));

        if (value != null && value instanceof String && ((String)value).length() != 0) {
            Matcher m = this.pattern.matcher((String)value);
            if(!m.matches()) {
                throw new ValidationException(this.message);
            }
        } else {
            LOGGER.warn("Email constraint input may not be right!");
        }

    }
}
