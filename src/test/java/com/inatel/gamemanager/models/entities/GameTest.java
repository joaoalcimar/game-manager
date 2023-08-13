package com.inatel.gamemanager.models.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatel.gamemanager.models.dtos.requests.GameRequest;
import com.inatel.gamemanager.utils.JsonConverterUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class GameTest {

    @Test
    @DisplayName("Test conversion between entity and dto")
    public void testOf() throws JsonProcessingException {
        String publisherId = "publisher123";
        String name = "Game Name";
        Map<String, Double> timePlayed = new HashMap<>();
        timePlayed.put("player1", 10.5);
        timePlayed.put("player2", 15.0);

        GameRequest request = GameRequest.builder()
                .publisherId(publisherId)
                .name(name)
                .timePlayed(timePlayed)
                .build();

        Game game = Game.of(request);

        assertNotNull(game);
        assertEquals(publisherId, game.getPublisherId());
        assertEquals(name, game.getName());

        String timesPlayed = JsonConverterUtil.convertMapToString(timePlayed);
        assertEquals(timesPlayed, game.getTimePlayed());
    }
}
