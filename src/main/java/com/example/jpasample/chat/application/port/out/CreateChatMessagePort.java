package com.example.jpasample.chat.application.port.out;

import com.example.jpasample.chat.domain.ChatMessage;

public interface CreateChatMessagePort {
    Long createChatMessage(ChatMessage chatMessage);
}
