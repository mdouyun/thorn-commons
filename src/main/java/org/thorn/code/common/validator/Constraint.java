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

/**
 * 数据校验规则约束接口，自定义约束类建议继承{@link BaseConstraintDefinition}类.
 *
 * @author chenyun.chris
 * @since 2018.05.08
 */
public interface Constraint {

    /**
     * 对传入的对象进行数据校验.
     *
     * @param value 需要校验的对象
     * @param checkArgs 注解定义的变量，可以为空，eg:min(10),length(16,18)
     * @throws ValidationException 当对象的值不满足校验要求时，则抛出异常，校验提示通过getMessage()获取
     */
    void check(Object value, String... checkArgs) throws ValidationException;

}
