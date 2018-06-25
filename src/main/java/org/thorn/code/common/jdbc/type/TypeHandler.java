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

package org.thorn.code.common.jdbc.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC操作中设置及获取值的接口，根据字段类型实现.
 *
 * @author chenyun.chris
 * @since 1.0
 */
public interface TypeHandler<T> {

    /**
     * 设置sql的查询条件值
     *
     * @param ps
     * @param i
     * @param parameter
     * @throws SQLException
     */
    void setParameter(PreparedStatement ps, int i, T parameter) throws SQLException;

    /**
     * 根据列的索引，获取结果集的字段值
     *
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    T getResult(ResultSet rs, int columnIndex) throws SQLException;

    /**
     * 根据列的名称，获取结果集的字段值
     *
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    T getResult(ResultSet rs, String columnName) throws SQLException;
}
