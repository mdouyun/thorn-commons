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


/**
 * null, "", 集合大小为0.
 *
 * @author chenyun.chris
 * @since 2018.05.10
 */
public class NotEmpty implements Constraint {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotEmpty.class);

    private final String message = "may not be empty";

    @Override
    public void check(Object value, String... checkArgs) throws ValidationException {
        LOGGER.debug("value:" , value , ",args:" , StringUtils.join(checkArgs));

        if(value == null) {
            throw new ValidationException(message);
        }

        if(value instanceof String && "".equals(value)) {
            throw new ValidationException(message);
        }

        if(value instanceof Collection && ((Collection) value).size() == 0) {
            throw new ValidationException(message);
        }


    }
}
