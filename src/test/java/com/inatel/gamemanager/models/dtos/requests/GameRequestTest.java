package com.inatel.gamemanager.models.dtos.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class GameRequestTest {

    @Test
    @DisplayName("Test default object creation using builder")
    public void testGameRequestBuilder() {
        String publisherId = "123";
        String name = "Test Game";
        Map<String, Double> timePlayed = new HashMap<>();
        timePlayed.put("10-10-01", 10.5);
        timePlayed.put("10-10-02", 20.0);

        GameRequest request = GameRequest.builder()
                .publisherId(publisherId)
                .name(name)
                .timePlayed(timePlayed)
                .build();

        assertNotNull(request);
        assertEquals(publisherId, request.getPublisherId());
        assertEquals(name, request.getName());
        assertEquals(timePlayed, request.getTimePlayed());
    }

    @Test
    @DisplayName("Test default object creation using constructor")
    public void testGameRequestGettersAndSetters() {
        GameRequest request = new GameRequest();
        String publisherId = "123";
        String name = "Test Game";
        Map<String, Double> timePlayed = new HashMap<>();
        timePlayed.put("10-10-01", 10.5);
        timePlayed.put("10-10-02", 20.0);

        request.setPublisherId(publisherId);
        request.setName(name);
        request.setTimePlayed(timePlayed);

        assertEquals(publisherId, request.getPublisherId());
        assertEquals(name, request.getName());
        assertEquals(timePlayed, request.getTimePlayed());
    }
}
