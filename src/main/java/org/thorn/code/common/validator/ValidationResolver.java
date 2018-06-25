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

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 规则解析类，将类属性的注解通过反射的方法解析到缓存中.
 *
 * @author chenyun.chris
 * @since 2018.05.08
 */
class ValidationResolver {

    static final Map<Class, ClassFieldsAnnotation> CF_MAPPING = new ConcurrentHashMap<Class, ClassFieldsAnnotation>(100);

    /**
     * 解析当前类及父类的注解字段，不递归解析属性字段.
     *
     * @param cls
     * @return
     */
    ClassFieldsAnnotation resolveAnnotation(Class cls) {

        ClassFieldsAnnotation cfa = CF_MAPPING.get(cls);

        if(cfa != null) {
            return cfa;
        }

        cfa = new ClassFieldsAnnotation(cls);

        for(Class c = cls; c != Object.class; c = c.getSuperclass()) {
            Field[] fs = c.getDeclaredFields();

            for(Field f : fs) {
                Validate va = f.getAnnotation(Validate.class);

                if(va != null) {
                    ValidateInfo info = new ValidateInfo(va);

                    String[] cons = va.value();
                    for(String cm : cons) {
                        info.addConstraintMethod(getConstraintName(cm), getConstraintArgs(cm));
                    }

                    cfa.put(f, info);
                }
            }

        }

        CF_MAPPING.put(cls, cfa);

        return CF_MAPPING.get(cls);
    }

    private String getConstraintName(String method) {

        if(StringUtils.isBlank(method)) {
            return null;
        }

        int st = method.indexOf("(");

        if(st == -1) {
            return method;
        }

        return StringUtils.substring(method, 0, st);
    }

    private String[] getConstraintArgs(String method) {

        if(StringUtils.isBlank(method)) {
            return null;
        }

        int st = method.indexOf("(");
        int end = method.lastIndexOf(")");

        if(st == -1 || end <= st) {
            return null;
        }

        String args = StringUtils.substring(method, st + 1, end);
        args = StringUtils.remove(args, " ");
        return StringUtils.split(args, ',');
    }


}
