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

package org.thorn.code.common.jdbc.type.impl;

import org.thorn.code.common.jdbc.type.BaseTypeHandler;
import org.thorn.code.common.jdbc.type.JDBCType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Long类型处理器.
 *
 * @author chenyun.chris
 * @since 1.0
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {

    @Override
    public int getJDBCType() {
        return JDBCType.BIGINT.TYPE_CODE;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long parameter) throws SQLException {
        ps.setLong(i, parameter);
    }

    @Override
    public Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getLong(columnName);
    }

    @Override
    public Long getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getLong(columnIndex);
    }
}
