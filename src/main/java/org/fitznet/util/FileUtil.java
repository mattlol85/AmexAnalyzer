package org.fitznet.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class FileUtil {
    public static String readResource(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) {
                log.error("File {} not found!" ,fileName);
                throw new IllegalArgumentException("File not found! " + fileName);
            } else {
                try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(isr)) {

                    return reader.lines().collect(Collectors.joining(System.lineSeparator()));
                }
            }
        } catch (Exception e) {
            log.error("Failed to read the file: {} {} ", fileName, e.getMessage());
            throw new RuntimeException("Failed to read the file: " + fileName, e);
        }
    }

}
