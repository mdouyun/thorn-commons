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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单个注解解析的约束关系类.
 *
 * @author chenyun.chris
 * @since 2018.05.14
 */
class ValidateInfo {

    public ValidateInfo(Validate va) {
        this.va = va;
    }

    private Validate va;

    private Map<String, String[]> constraintMap = new HashMap<String, String[]>();

    Validate getVa() {
        return va;
    }

    void setVa(Validate va) {
        this.va = va;
    }

     void addConstraintMethod(String name, String[] args) {
        this.constraintMap.put(name, args);
    }

     Set<String> getMethodNames() {

        return this.constraintMap.keySet();
    }

     String[] getArgsByMethod(String name) {
        return this.constraintMap.get(name);
    }
}
