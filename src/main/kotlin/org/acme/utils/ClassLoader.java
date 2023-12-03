package org.acme.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ClassLoader {
    public static File getFile(String name) throws IOException {
        java.lang.ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(name);
        System.out.println(classloader.getResource(name));
        File file = null;
        FileUtils.copyInputStreamToFile(is, file);
        return file;
    }
}
