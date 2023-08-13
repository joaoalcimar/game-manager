package com.inatel.gamemanager.services;

import com.inatel.gamemanager.clients.publishermanager.PublisherManagerClient;
import com.inatel.gamemanager.exceptions.EmptyStringException;
import com.inatel.gamemanager.exceptions.InvalidPublisherException;
import com.inatel.gamemanager.exceptions.TimeFormatException;
import com.inatel.gamemanager.exceptions.ValidationException;
import com.inatel.gamemanager.models.dtos.requests.GameRequest;
import com.inatel.gamemanager.models.dtos.responses.GameResponse;
import com.inatel.gamemanager.models.entities.Game;
import com.inatel.gamemanager.repositories.GameManagementRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GameManagementServiceTest {

    private final GameManagementRepository gameManagementRepository = mock(GameManagementRepository.class);

    private final PublisherManagerClient publisherManagerClient = mock(PublisherManagerClient.class);

    private GameManagementService gameManagementService;

    @Before
    public void setUp() {
        gameManagementService = new GameManagementService(gameManagementRepository, publisherManagerClient);
    }

    @Test
    @DisplayName("Test save working successfully")
    public void testSaveSuccess(){
        GameRequest request = new GameRequest();
        request.setPublisherId("publisher1");
        request.setName("Game 1");
        Map<String, Double> timePlayed = new HashMap<>();
        timePlayed.put("2023-08-12", 5.5);
        request.setTimePlayed(timePlayed);

        when(publisherManagerClient.getPublishersAllowList()).thenReturn(Collections.singletonMap("publisher1", "Publisher 1"));
        when(gameManagementRepository.save(any(Game.class))).thenReturn(new Game());

        assertDoesNotThrow(() -> gameManagementService.save(request));

        verify(publisherManagerClient, times(1)).getPublishersAllowList();
        verify(gameManagementRepository, times(1)).save(any(Game.class));
    }

    @Test
    @DisplayName("Test save with invalid publisher id")
    public void testSaveInvalidPublisherId() {
        GameRequest request = new GameRequest();
        request.setPublisherId("invalidPublisher");
        request.setName("Game 1");
        Map<String, Double> timePlayed = new HashMap<>();
        timePlayed.put("2023-08-12", 5.5);
        request.setTimePlayed(timePlayed);

        when(publisherManagerClient.getPublishersAllowList()).thenReturn(Collections.singletonMap("publisher1", "Publisher 1"));

        assertThrows(InvalidPublisherException.class, () -> gameManagementService.save(request));

        verify(publisherManagerClient, times(1)).getPublishersAllowList();
        verify(gameManagementRepository, never()).save(any(Game.class));
    }

    @Test
    @DisplayName("Test find all successfully")
    public void testFindAll() {
        Game game1 = new Game();
        game1.setId(new UUID(1L, 2L));
        game1.setPublisherId("publisher1");
        game1.setName("Game 1");

        Game game2 = new Game();
        game2.setId(new UUID(1L, 2L));
        game2.setPublisherId("publisher2");
        game2.setName("Game 2");

        List<Game> gameList = Arrays.asList(game1, game2);

        when(gameManagementRepository.findAll()).thenReturn(gameList);

        List<GameResponse> responseList = gameManagementService.findAll();

        assertEquals(2, responseList.size());
        assertEquals("publisher1", responseList.get(0).getPublisherId());
        assertEquals("Game 1", responseList.get(0).getName());
        assertEquals("publisher2", responseList.get(1).getPublisherId());
        assertEquals("Game 2", responseList.get(1).getName());

        verify(gameManagementRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test find by id successfully")
    public void testFindByPublisherId() {
        String publisherId = "publisher1";

        Game game1 = new Game();
        game1.setId(new UUID(1L, 2L));
        game1.setPublisherId(publisherId);
        game1.setName("Game 1");

        Game game2 = new Game();
        game2.setId(new UUID(1L, 2L));
        game2.setPublisherId(publisherId);
        game2.setName("Game 2");

        List<Game> gameList = Arrays.asList(game1, game2);

        when(gameManagementRepository.findByPublisherIdIgnoreCaseContaining(publisherId)).thenReturn(gameList);

        List<GameResponse> responseList = gameManagementService.findByPublisherId(publisherId);

        assertEquals(2, responseList.size());
        assertEquals(publisherId, responseList.get(0).getPublisherId());
        assertEquals("Game 1", responseList.get(0).getName());
        assertEquals(publisherId, responseList.get(1).getPublisherId());
        assertEquals("Game 2", responseList.get(1).getName());

        verify(gameManagementRepository, times(1)).findByPublisherIdIgnoreCaseContaining(publisherId);
    }

    @Test
    @DisplayName("Test validation with valid publisher id")
    public void testValidatePublisherIdParameterValidPublisherId() {
        String publisherId = "publisher1";
        gameManagementService.validatePublisherIdParameter(publisherId);
    }

    @Test
    @DisplayName("Test validation with null publisher id")
    public void testValidatePublisherIdParameterNullPublisherId() {
        String publisherId = null;
        assertThrows(EmptyStringException.class, () -> gameManagementService.validatePublisherIdParameter(publisherId));
    }

    @Test
    @DisplayName("Test validation with valid empty id")
    public void testValidatePublisherIdParameterEmptyPublisherId() {
        String publisherId = "";
        assertThrows(EmptyStringException.class, () -> gameManagementService.validatePublisherIdParameter(publisherId));
    }

    @Test
    @DisplayName("Test validation with publisher id in allow list")
    public void testValidatePublisherIdInPublisherApiValidPublisherId() {
        String publisherId = "publisher1";
        Map<String, String> publishersAllowList = new HashMap<>();
        publishersAllowList.put("publisher1", "Publisher 1");
        when(publisherManagerClient.getPublishersAllowList()).thenReturn(publishersAllowList);

        assertDoesNotThrow(() -> gameManagementService.validatePublisherIdInPublisherApi(publisherId));
    }

    @Test
    @DisplayName("Test validation with publisher id not in allow list")
    public void testValidatePublisherIdInPublisherApiInvalidPublisherId() {
        String publisherId = "invalid_publisher";
        Map<String, String> publishersAllowList = new HashMap<>();
        when(publisherManagerClient.getPublishersAllowList()).thenReturn(publishersAllowList);

        assertThrows(InvalidPublisherException.class, () -> gameManagementService.validatePublisherIdInPublisherApi(publisherId));
        verify(publisherManagerClient, times(1)).getPublishersAllowList();
    }

    @Test
    @DisplayName("Test validation with valid game name")
    public void testValidateNameValidName() {
        String name = "Sample Game";

        assertDoesNotThrow(() -> gameManagementService.validateName(name));
    }

    @Test
    @DisplayName("Test validation with empty game name")
    public void testValidateNameEmptyName() {
        String name = "";

        assertThrows(EmptyStringException.class, () -> gameManagementService.validateName(name));
    }


    @Test
    @DisplayName("Test validation with valid time played")
    public void testValidateTimePlayedValidData() {
        Map<String, Double> timePlayed = new HashMap<>();
        timePlayed.put("2023-08-12", 5.5);

        assertDoesNotThrow(() -> gameManagementService.validateTimePlayed(timePlayed));
    }

    @Test
    @DisplayName("Test validation with not ISO time played")
    public void testValidateTimePlayedInvalidDateFormat() {
        Map<String, Double> timePlayed = new HashMap<>();
        timePlayed.put("12-08-2023", 5.5);

        assertThrows(TimeFormatException.class, () -> gameManagementService.validateTimePlayed(timePlayed));
    }

    @Test
    @DisplayName("Test validation with negative time played")
    public void testValidateTimePlayedNegativeHours() {
        Map<String, Double> timePlayed = new HashMap<>();
        timePlayed.put("2023-08-12", -2.0);

        assertThrows(ValidationException.class, () -> gameManagementService.validateTimePlayed(timePlayed));
    }

    @Test
    @DisplayName("Test validation with zero time played")
    public void testValidateTimePlayedZeroHours() {
        Map<String, Double> timePlayed = new HashMap<>();
        timePlayed.put("2023-08-12", 0.0);

        assertThrows(ValidationException.class, () -> gameManagementService.validateTimePlayed(timePlayed));
    }

}
