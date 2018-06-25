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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 类对象字段注解映射关系类.
 *
 * @author chenyun.chris
 * @since 2018.05.08
 */
class ClassFieldsAnnotation {


    ClassFieldsAnnotation(Class cls) {
        this.cls = cls;
    }

    private Class cls;

    /**
     * 类属性是否有注解，一个都没有则为false
     */
    private boolean hasAnnotation = false;

    /**
     * 类属性与注解的映射关系Map
     */
    private Map<Field, ValidateInfo> fa = new HashMap<Field, ValidateInfo>();

    void put(Field f, ValidateInfo v) {
        this.fa.put(f, v);

        if(!hasAnnotation) {
            hasAnnotation = true;
        }

    }

    Set<Field> getAnnotationFields() {
        return fa.keySet();
    }

    ValidateInfo getAnnotation(Field f) {
        return fa.get(f);
    }


    boolean isHasAnnotation() {
        return hasAnnotation;
    }
}
