package com.inatel.gamemanager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonConverterUtil {

    private JsonConverterUtil(){
    }

    public static String convertMapToString(Map<String, Double> map) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String stringConverted = objectMapper.writeValueAsString(map);

        return stringConverted;
    }
}
