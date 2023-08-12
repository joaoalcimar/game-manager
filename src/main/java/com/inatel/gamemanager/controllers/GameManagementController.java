package com.inatel.gamemanager.controllers;

import com.inatel.gamemanager.models.dtos.requests.GameRequest;
import com.inatel.gamemanager.models.dtos.responses.GameResponse;
import com.inatel.gamemanager.services.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameManagementController {

    @Autowired
    private GameManagementService gameManagementService;

    @PostMapping
    public GameResponse save(@RequestBody GameRequest request){
        return gameManagementService.save(request);
    }

    //since in the documentation they are mapped to the same route
    @GetMapping
    public List<GameResponse> findByPublisher(@RequestParam(required = false) String publisherId){
        if (publisherId != null){
            return gameManagementService.findByPublisherId(publisherId);
        }else {
            return gameManagementService.findAll();
        }
    }
}
