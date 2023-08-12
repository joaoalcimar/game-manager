package com.inatel.gamemanager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonConverterUtil {

    private JsonConverterUtil(){
    }

    public static String convertMapToString(Map<String, Double> map) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String convertedString = objectMapper.writeValueAsString(map);

        return convertedString;
    }

    public static Map<String, String> convertStringToMapFromClientResponse(String jsonArray) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> list = objectMapper.readValue(
                jsonArray, new TypeReference<>() {
                }
        );

        Map<String, String> resultMap = list.stream()
                .collect(Collectors.toMap(map -> map.get("id"), map -> map.get("name")));

        return resultMap;
    }
}
