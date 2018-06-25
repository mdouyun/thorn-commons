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
import org.thorn.code.common.validator.constraint.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 约束类的工厂方法，采用单例模式，所有的约束类需要通过{@link ConstraintFactory#register(Constraint)}注册.
 *
 * @author chenyun.chris
 * @since 2018.05.09
 */
public class ConstraintFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConstraintFactory.class);

    private static ConstraintFactory factory = new ConstraintFactory();

    private Map<String, Constraint> constraintMap = new ConcurrentHashMap<String, Constraint>();

    private ConstraintFactory() {
        // system init
        constraintMap.put("notNull", new NotNull());
        constraintMap.put("notEmpty", new NotEmpty());
        constraintMap.put("email", new Email());
        constraintMap.put("future", new Future());
        constraintMap.put("past", new Past());
        constraintMap.put("length", new Length());
        constraintMap.put("pattern", new Pattern());
        constraintMap.put("min", new Min());
        constraintMap.put("max", new Max());
    }

    public static ConstraintFactory getInstance() {
        return factory;
    }

    /**
     * 根据注解中的名称找到约束类
     *
     * @param name 注解中使用的名称，min或Min
     * @return 对应的约束类
     */
    public Constraint getConstraint(String name) {

        String firstLetter = String.valueOf(name.charAt(0));
        name = name.replaceFirst(firstLetter, firstLetter.toLowerCase());

        return constraintMap.get(name);
    }

    /**
     * 将约束实例注册到工厂类中，key值为类的名字并将首字母小写
     *
     * @param constraint
     */
    public void register(Constraint constraint) {
        String name = constraint.getClass().getSimpleName();

        String firstLetter = String.valueOf(name.charAt(0));
        name = name.replaceFirst(firstLetter, firstLetter.toLowerCase());

        if(constraintMap.containsKey(name)) {
            LOGGER.warn(name, " will override constraint'map!");
        }

        constraintMap.put(name, constraint);
    }




}
