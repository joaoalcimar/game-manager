package com.inatel.gamemanager.models.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatel.gamemanager.models.dtos.requests.GameRequest;
import com.inatel.gamemanager.utils.JsonConverterUtil;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "publisher_id", nullable = false)
    private String publisherId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "timePlayed", nullable = false)
    private String timePlayed;

    public static Game of(GameRequest request) throws JsonProcessingException {
        Game game = new Game();
        BeanUtils.copyProperties(request, game);

        String timesPlayed = JsonConverterUtil.convertMapToString(request.getTimePlayed());
        game.setTimePlayed(timesPlayed);

        return game;
    }

}
