package com.inatel.gamemanager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class JsonConverterUtilTest {

    @Test
    @DisplayName("Test the map stringify")
    public void testConvertMapToString() throws JsonProcessingException {
        Map<String, Double> map = new HashMap<>();
        map.put("key1", 1.0);
        map.put("key2", 2.5);

        String convertedString = JsonConverterUtil.convertMapToString(map);
        String expectedResult = "{\"key1\":1.0,\"key2\":2.5}";

        assertNotNull(convertedString);
        assertEquals(expectedResult, convertedString);
    }

    @Test
    @DisplayName("Test string json to map object conversion")
    public void testConvertStringToMapFromClientResponse() throws JsonProcessingException {
        String jsonArray = "[{\"id\":\"1\",\"name\":\"John\"},{\"id\":\"2\",\"name\":\"Jane\"}]";

        Map<String, String> resultMap = JsonConverterUtil.convertStringToMapFromClientResponse(jsonArray);

        assertNotNull(resultMap);
        assertEquals("John", resultMap.get("1"));
        assertEquals("Jane", resultMap.get("2"));
    }
}