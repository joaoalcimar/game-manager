package com.inatel.gamemanager.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatel.gamemanager.clients.publishermanager.PublisherManagerClient;
import com.inatel.gamemanager.exceptions.EmptyStringException;
import com.inatel.gamemanager.exceptions.InvalidPublisherException;
import com.inatel.gamemanager.exceptions.TimeFormatException;
import com.inatel.gamemanager.exceptions.ValidationException;
import com.inatel.gamemanager.models.entities.Game;
import com.inatel.gamemanager.models.dtos.requests.GameRequest;
import com.inatel.gamemanager.models.dtos.responses.GameResponse;
import com.inatel.gamemanager.repositories.GameManagementRepository;
import com.inatel.gamemanager.utils.TimeFormatterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class GameManagementService {

    @Autowired
    private GameManagementRepository gameManagementRepository;

    @Autowired
    private PublisherManagerClient publisherManagerClient;

    @Transactional
    public GameResponse save(GameRequest request){
        validatePublisherId(request.getPublisherId());
        validateName(request.getName());
        validateTimePlayed(request.getTimePlayed());

        Game game;

        try{
            game = gameManagementRepository.save(Game.of(request));
        }catch (JsonProcessingException e){
            throw new ValidationException("The JSON formatting is incorrect.");
        }

        return GameResponse.of(game);
    }

    public List<GameResponse> findAll() {
        return gameManagementRepository.findAll()
                .stream()
                .map(GameResponse::of)
                .collect(Collectors.toList());
    }

    //Supports similar results and case-insensitive
    public List<GameResponse> findByPublisherId(String publisherId) {
        validatePublisherId(publisherId);

        return gameManagementRepository.findByPublisherIdIgnoreCaseContaining(publisherId)
                .stream()
                .map(GameResponse::of)
                .collect(Collectors.toList());
    }

    protected void validatePublisherId(String publisherId) {
        if(isEmpty(publisherId)){
            throw new EmptyStringException("The game publisher id must to be informed.");
        }

        if(!publisherManagerClient.isPublisherValid(publisherId)){
            throw new InvalidPublisherException("This publisher id is not allowed. Contact Support.");
        }
    }

    protected void validateName(String name) {
        if(isEmpty(name)){
            throw new EmptyStringException("The game name must to be informed.");
        }
    }

    protected void validateTimePlayed(Map<String, Double> timePlayedEntity) {

        for (Map.Entry<String, Double> entry : timePlayedEntity.entrySet()) {
            if (!TimeFormatterUtil.isISODateFormat(entry.getKey())) {
                throw new TimeFormatException("The date format must be in ISO format YYYY-MM-DD, i.e. \"2000-01-30\".");
            }

            Double hoursPlayed = entry.getValue();

            if (hoursPlayed <= 0) {
                throw new ValidationException("The provided hours must be greater than zero.");
            }

        }

    }
}
