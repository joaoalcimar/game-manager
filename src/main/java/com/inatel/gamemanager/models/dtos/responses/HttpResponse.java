package com.inatel.gamemanager.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class HttpResponse {

    String message;
    HttpStatusCode httpStatus;
}
