package com.inatel.gamemanager.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameRequest {

    private String publisherId;

    private String name;

    private Map<String, Double> timePlayed;
}
