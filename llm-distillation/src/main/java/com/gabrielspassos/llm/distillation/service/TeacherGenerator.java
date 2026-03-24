package com.gabrielspassos.llm.distillation.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class TeacherGenerator {

    private final ChatClient chatClient;

    public TeacherGenerator(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateAnswer(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }

}
