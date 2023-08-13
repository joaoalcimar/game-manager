package com.inatel.gamemanager.clients.publishermanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterRequestBody {

    private String host;

    private Integer port;
}
