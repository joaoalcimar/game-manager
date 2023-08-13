package com.inatel.gamemanager.models.dtos.responses;

import com.inatel.gamemanager.models.entities.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class GameResponseTest {

    @Test
    @DisplayName("Test default object creation")
    public void testGameResponseOfMethod() {
        UUID id = UUID.randomUUID();
        String publisherId = "123";
        String name = "Test Game";
        String timePlayed = "{\"10-10-10\": 10.5, \"10-10-11\": 20.0}";

        Game game = new Game();
        game.setId(id);
        game.setPublisherId(publisherId);
        game.setName(name);
        game.setTimePlayed(timePlayed);

        GameResponse response = GameResponse.of(game);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(publisherId, response.getPublisherId());
        assertEquals(name, response.getName());
        assertEquals(timePlayed, response.getTimePlayed());
    }
}
