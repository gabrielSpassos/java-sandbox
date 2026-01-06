package com.gabrielspassos.api.v1.controllers;

import com.gabrielspassos.api.v1.requests.MessageRequest;
import com.gabrielspassos.api.v1.responses.MessageResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageController {

    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yy HH:mm:ss");

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    MessageResponse message(MessageRequest request) {
        String dateTime = DATE_FORMATTER.format(new Date());
        return new MessageResponse(request.getFrom(), request.getMessage(), dateTime);
    }
}
