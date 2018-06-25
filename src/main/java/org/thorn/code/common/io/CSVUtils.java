/*
 * Copyright 2002-2009 the original author or authors.
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
package org.thorn.code.common.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * CSV相关工具类.
 *
 * @author chenyun by 2018-06-25 10:40
 * @since JDK1.6
 */
public class CSVUtils {

    private static String DEFAULT_SEPARATOR = ",";

    public static void writeRow(String[] row, File csv, String separator) throws IOException {

        StringBuilder r = new StringBuilder();

        for (int i = 0; i < row.length; i++) {
            r.append(StringUtils.defaultString(row[i], ""));

            if (i < row.length - 1) {
                r.append(separator);
            }
        }
        r.append("\r\n");

        FileUtils.writeStringToFile(csv, r.toString(), true);
    }

    public static void writeRow(String[] row, File csv) throws IOException {
        CSVUtils.writeRow(row, csv, DEFAULT_SEPARATOR);
    }

    public static void writeAll(List<String[]> txt, File csv, String separator) throws IOException {
        StringBuilder r = new StringBuilder();

        for(String[] row : txt) {
            for (int i = 0; i < row.length; i++) {
                r.append(StringUtils.defaultString(row[i], ""));

                if (i < row.length - 1) {
                    r.append(separator);
                }
            }
            r.append("\r\n");
        }

        FileUtils.writeStringToFile(csv, r.toString(), true);
    }

    public static void writeAll(List<String[]> txt, File csv) throws IOException {
        CSVUtils.writeAll(txt, csv, DEFAULT_SEPARATOR);
    }

}
