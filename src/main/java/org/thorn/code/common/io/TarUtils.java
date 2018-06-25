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

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * tar文件操作.
 *
 * @author chenyun by 2018-06-25 11:15
 * @since JDK1.6
 */
public class TarUtils {

    /**
     * tar文件及文件夹。
     *
     * @param files  需要压缩的文件及文件夹列表
     * @param tarFile  生成的压缩文件
     * @param pName  生成的压缩文件是否需要以目录开始
     * @throws java.io.IOException
     */
    public static void tar(File tarFile, File[] files, String pName) throws IOException {

        if(!tarFile.exists()) {
            tarFile.createNewFile();
        }

        OutputStream out = null;
        TarArchiveOutputStream tarOut = null;
        try {
            out = new FileOutputStream(tarFile);
            tarOut = new TarArchiveOutputStream(out);

            if(StringUtils.isNotBlank(pName)) {

                if(!pName.endsWith("/")) {
                    pName = pName + "/";
                }

                TarArchiveEntry tarEntry = new TarArchiveEntry(pName);
                tarOut.putArchiveEntry(tarEntry);
                tarOut.closeArchiveEntry();
            }

            for(File file : files) {
                addTarFile(tarOut, file, pName);
            }

            tarOut.flush();
            tarOut.finish();
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(tarOut);
        }
    }

    /**
     * 通过递归的方式将文件添加到压缩文件中。
     *
     * @param tarOut  压缩文件的输出流
     * @param file  需要压缩的文件
     * @param pName  压缩文件的父文件夹路径，无父文件夹为""
     * @throws IOException
     */
    private static void addTarFile(TarArchiveOutputStream tarOut, File file, String pName) throws IOException {

        if(file.isDirectory()) {
            pName = pName + file.getName() + "/";

            File[] files = file.listFiles();

            for(File child : files) {
                addTarFile(tarOut, child, pName);
            }
        } else {

            String name = pName + file.getName();

            TarArchiveEntry tarEntry = new TarArchiveEntry(file, name);
            tarOut.putArchiveEntry(tarEntry);

            InputStream is = new FileInputStream(file);
            try {
                IOUtils.copy(is, tarOut);
            } finally {
                IOUtils.closeQuietly(is);
                tarOut.closeArchiveEntry();
            }
        }
    }

    /**
     * 对tar文件进行gzip压缩
     *
     * @param tar
     * @param gzip
     * @throws IOException
     */
    public static void gzip(File tar, File gzip) throws IOException {

        FileOutputStream gzFile = null;
        GZIPOutputStream gzOut = null;
        FileInputStream tarIn = null;
        try {
            gzFile = new FileOutputStream(gzip);
            gzOut = new GZIPOutputStream(gzFile);
            tarIn = new FileInputStream(tar);

            IOUtils.copy(tarIn, gzOut);
        } finally {
            IOUtils.closeQuietly(gzOut);
            IOUtils.closeQuietly(gzFile);
            IOUtils.closeQuietly(tarIn);
        }
    }

}
