package com.tyrell.replicant.user.service.utils;

import com.tyrell.replicant.user.service.model.User;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.SPACE;

public class InitialDataLoaderUtils {

    public static List<User> convertCSVFileToUserList(String csvFile) throws IOException {
        List<User> users = new ArrayList<>();
        User userBean = null;
        CellProcessor[] processors = new CellProcessor[]{null, null, null, null, null, null, null, null, null, null, null};
        ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(csvFile), CsvPreference.STANDARD_PREFERENCE);
        String[] raw = beanReader.getHeader(true);
        String[] header = convertStringArrayToCamelCase(raw);
        while ((userBean = beanReader.read(User.class, header, processors)) != null) {
            users.add(userBean);
        }
        return users;
    }

    private static String[] convertStringArrayToCamelCase(String input[]) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            if (input[i].contains(SPACE)) {
                result[i] = convertStringToCamelCase(input[i]);
            } else {
                result[i] = input[i].toLowerCase();
            }
        }
        return result;
    }

    private static String convertStringToCamelCase(String titleCased) {
        String[] words = titleCased.split(SPACE);
        StringBuilder camelCased = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String str = new String(words[i].toLowerCase());
            if (i == 0) {
                camelCased.append(str);
            } else {
                camelCased.append(new String(str.substring(0, 1).toUpperCase() + str.substring(1)));
            }
        }
        return camelCased.toString();
    }

}