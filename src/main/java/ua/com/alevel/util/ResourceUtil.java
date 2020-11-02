package ua.com.alevel.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 7:56 PM
 */
public class ResourceUtil {

    private static final String CORONE_PROPERTIES = "corone.properties";
    private static final String CORONE_DATASOURCE = "corone.datasource.";

    public static Map<String, String> getResource(Class<?> aClass) {
        int first = CORONE_DATASOURCE.length();
        URL systemResource = aClass.getClassLoader().getSystemResource(CORONE_PROPERTIES);
        String path = systemResource.getPath();
        try {
            Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
            return lines
                    .filter(StringUtils::isNotBlank)
                    .filter(line -> !line.startsWith("#"))
                    .map(line -> line.substring(first))
                    .map(line -> line.split("="))
                    .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден: " + e.getMessage());
        }
    }
}
