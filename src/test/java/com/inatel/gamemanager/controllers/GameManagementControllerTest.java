package com.inatel.gamemanager.controllers;

import com.inatel.gamemanager.TestUtils;
import com.inatel.gamemanager.models.dtos.responses.GameResponse;
import com.inatel.gamemanager.services.GameManagementService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebMvcTest(GameManagementController.class)
public class GameManagementControllerTest {

    @MockBean
    private GameManagementService gameManagementService;

    @Autowired
    private MockMvc mvc;

    private final String GAME_MANAGEMENT_CONTROLLER_ROUTE = "/game";

    @Test
    @DisplayName("Test save game post endpoint")
    public void testSave() throws Exception {
        mvc.perform(
                post(GAME_MANAGEMENT_CONTROLLER_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.returnValidRequestBody()))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test find publisher by id get endpoint")
    public void findPublisherById() throws Exception {

        GameResponse gameResponse = new GameResponse();
        gameResponse.setPublisherId("capcom");
        gameResponse.setName("megaman");
        gameResponse.setId(new UUID(1L, 2L));
        gameResponse.setTimePlayed(TestUtils.returnValidTimePlayedString());

        List<GameResponse> list = new ArrayList<>();
        list.add(gameResponse);

        when(gameManagementService.findByPublisherId(any())).thenReturn(list);

        mvc.perform(
                get(GAME_MANAGEMENT_CONTROLLER_ROUTE)
                        .param("publisherId", "capcom"))
                        .andExpect(status().isOk())
                        .andExpect(content().string(TestUtils.returnValidResponseBody()));
    }

    @Test
    @DisplayName("Test find all publishers get endpoint")
    public void findAllPublishers() throws Exception {

        GameResponse gameResponse = new GameResponse();
        gameResponse.setPublisherId("capcom");
        gameResponse.setName("megaman");
        gameResponse.setId(new UUID(1L, 2L));
        gameResponse.setTimePlayed(TestUtils.returnValidTimePlayedString());

        List<GameResponse> list = new ArrayList<>();
        list.add(gameResponse);

        when(gameManagementService.findAll()).thenReturn(list);

        mvc.perform(
                        get(GAME_MANAGEMENT_CONTROLLER_ROUTE))
                .andExpect(status().isOk())
                .andExpect(content().string(TestUtils.returnValidResponseBody()));
    }
}
