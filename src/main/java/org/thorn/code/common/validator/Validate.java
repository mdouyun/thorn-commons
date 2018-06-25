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

import java.lang.annotation.*;

/**
 * 字段校验注解，规则名称为约束类的类名小写形式.
 * eg:@validate({"email","min(2)"}).
 *
 * @see org.thorn.code.commons.validator.constraint.Email
 * @see org.thorn.code.commons.validator.constraint.Past
 * @see org.thorn.code.commons.validator.constraint.Future
 * @see org.thorn.code.commons.validator.constraint.Length
 * @see org.thorn.code.commons.validator.constraint.Max
 * @see org.thorn.code.commons.validator.constraint.Min
 * @see org.thorn.code.commons.validator.constraint.NotEmpty
 * @see org.thorn.code.commons.validator.constraint.NotNull
 * @see org.thorn.code.commons.validator.constraint.Pattern
 *
 * @author chenyun.chris
 * @since 2018.05.08
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

    String[] value();

    String format() default "{name}:{error}";
}
