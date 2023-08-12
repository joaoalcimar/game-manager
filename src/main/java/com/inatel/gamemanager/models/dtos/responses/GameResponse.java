package com.inatel.gamemanager.models.dtos.responses;

import com.inatel.gamemanager.models.entities.Game;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@NoArgsConstructor
@Data
public class GameResponse {

    private UUID id;

    private String publisherId;

    private String name;

    private String timePlayed;

    public static GameResponse of(Game game){
        GameResponse response = new GameResponse();
        BeanUtils.copyProperties(game, response);
        return response;
    }

}
