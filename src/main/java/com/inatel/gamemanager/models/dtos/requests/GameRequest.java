package com.inatel.gamemanager.models.dtos.requests;

import lombok.Data;

import java.util.Map;

@Data
public class GameRequest {

    private String publisherId;

    private String name;

    private Map<String, Double> timePlayed;
}
