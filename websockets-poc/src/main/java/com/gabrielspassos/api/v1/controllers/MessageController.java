package com.gabrielspassos.api.v1.controllers;

import com.gabrielspassos.api.v1.requests.MessageRequest;
import com.gabrielspassos.api.v1.requests.MessageWithSessionRequest;
import com.gabrielspassos.api.v1.responses.MessageResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
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

    @MessageMapping("/private")
    @SendToUser("/topic/messages")
    MessageResponse messagePrivate(MessageWithSessionRequest request, SimpMessageHeaderAccessor headerAccessor) {
        if (null != request.getSessionId() && !request.getSessionId().isEmpty()) {
            headerAccessor.setSessionId(request.getSessionId());
        }
        String dateTime = DATE_FORMATTER.format(new Date());
        return new MessageResponse(request.getFrom(), request.getMessage(), dateTime);
    }

    @MessageMapping("/connected")
    @SendToUser("/topic/connected")
    String connected(SimpMessageHeaderAccessor headerAccessor) {
        return "Connected with SessionId: " + headerAccessor.getSessionId();
    }
}
