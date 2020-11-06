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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thorn.code.common.exception.ValidationException;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 对象校验类，通过实例化该类，根据对象属性的注解规则，validate方法实现对象属性的校验.
 * 如果属性未标记{@link Validate}注解，则该字段不会进行校验；
 * 注解的规则配置错误则会跳过错误的配置，日志打印warn错误；
 *
 * @author chenyun.chris
 * @since 2018.05.09
 */
public class Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

    private ValidationResolver resolver;

    private ConstraintFactory factory;

    private MessageFormat messageFormat;

    public Validator() {

        this.messageFormat = new MessageFormat();
        this.resolver = new ValidationResolver();
        this.factory = ConstraintFactory.getInstance();
    }

    /**
     * 对传入的对象进行数据校验，如果校验对象为null，则返回根对象为空的错误提示.
     * 自定义对象、数组、列表、Map会级联校验，前提是字段带有{@link Validate}注解，eg:
     * <code>
     *
     *     @Validate("isNotNull")
     *     private List<Person> list;
     *
     * </code>
     *
     * @param value 需要数据校验的对象
     * @return  错误提示的集合，如果集合大小为空，则表示校验通过
     */
    public Vector<String> validate(Object value) {

        Vector<String> error = new Vector<String>();

        if(value == null) {
            error.add(this.messageFormat.getRootNullMessage());
            return error;
        }

        error.addAll(objectValidate(value, value.getClass().getSimpleName()));

        return error;

    }

    /**
     * 递归对象校验.
     *
     * @param fv
     * @param parent
     * @return
     */
    private Vector<String> objectValidate(Object fv, String parent) {

        // 通过该方法的递归，将类的解析工作同步递归完成
        ClassFieldsAnnotation cfa = resolver.resolveAnnotation(fv.getClass());
        if(!cfa.isHasAnnotation()) {
            return null;
        }

        Set<Field> fields = cfa.getAnnotationFields();

        Vector<String> error = new Vector<String>();

        for(Field f : fields) {

            ValidateInfo va = cfa.getAnnotation(f);
            f.setAccessible(true);

            try {
                Object v = f.get(fv);
                error.addAll(fieldValidate(f, v, va, parent));
            } catch (IllegalAccessException e) {
                LOGGER.warn("Get field value error, field: {}, error: {}", f.getName(), e.getMessage());
            }
        }

        return error;
    }

    /**
     * 递归字段校验.
     *
     * @param f
     * @param fv
     * @param va
     * @param parent
     * @return
     */
    private Vector<String> fieldValidate(Field f, Object fv, ValidateInfo va, String parent) {

        Set<String> methodNames = va.getMethodNames();

        Vector<String> error = new Vector<String>();

        for(String name : methodNames) {

            String[] args = va.getArgsByMethod(name);

            Constraint constraint = this.factory.getConstraint(name);
            if(constraint == null) {
                LOGGER.warn("Validate annotation name not found, name:{}", name);
                continue;
            }
            String format = va.getVa().format();
            try {
                constraint.check(fv, args);
            } catch (ValidationException e) {
                String tips = this.messageFormat.format(
                        e.getMessage(), parent + "." + f.getName(), format);
                error.add(tips);
            }

            if(fv != null && !isSkipClass(fv)) {

                if(fv.getClass().isArray()) {

                    Object[] array = (Object[]) fv;
                    for(int i = 0; i < array.length ; i++) {
                        error.addAll(objectValidate(array[i],
                                parent + "." + f.getName() + "[" + i + "]"));
                    }

                } else if(fv instanceof Collection) {

                    Collection cl = (Collection) fv;

                    Iterator iterator = cl.iterator();
                    int i = 0;
                    while(iterator.hasNext()) {
                        Object o = iterator.next();
                        error.addAll(objectValidate(o,
                                parent + "." + f.getName() + "[" + i + "]"));
                        i++;
                    }
                } else if(fv instanceof Map) {
                    Map map = (Map) fv;

                    Set keys = map.keySet();
                    for(Object key : keys) {
                        Object value = map.get(key);
                        error.addAll(objectValidate(value,
                                parent + "." + f.getName() + "[" + key + "]"));
                    }

                } else {
                    error.addAll(objectValidate(fv, parent + "." + f.getName()));
                }
            }

        }

        return error;
    }

    /**
     * 判断对象是否是潜在的注解对象.
     * jdk的类排除掉，数组对象进一步递归判断元素的Class对象.
     * 内部类或者匿名类暂时未考虑.
     *
     * @param fv
     * @return
     */
    private boolean isSkipClass(Object fv) {

        Class cls = fv.getClass();
        String name = cls.getName();

        if(fv instanceof Collection || fv instanceof Map) {
            return false;
        }

        if(cls.isEnum() || cls.isPrimitive() || name.startsWith("java.") || name.startsWith("javax.")) {
            return true;
        }

        if(cls.isArray()) {
            Class eCls = cls.getComponentType();
            name = cls.getName();
            if(eCls.isEnum() || eCls.isPrimitive()
                    || name.startsWith("java.") || name.startsWith("javax.")) {
                return true;
            }
        }

        return false;
    }

}
