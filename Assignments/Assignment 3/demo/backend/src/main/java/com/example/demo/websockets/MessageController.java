package com.example.demo.websockets;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/consult")
    @SendTo("/topic/consult")
    public Message mesaj(Message message)
    {
        return new Message(message.getMessage());
    }
}
