package com.mobile.framework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static Object[][] readLoginData(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, String>> data = mapper.readValue(new File(filePath), new TypeReference<>() {});
            Object[][] result = new Object[data.size()][2];
            for (int i = 0; i < data.size(); i++) {
                result[i][0] = data.get(i).get("username");
                result[i][1] = data.get(i).get("password");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[0][];
        }
    }
}
