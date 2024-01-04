package com.example.jpasample.chat.application.port.in;

import com.example.jpasample.chat.application.port.in.command.ChatMessageCreateCommand;

public interface ChatMessageCreateUseCase {
    Long createChatMessage(ChatMessageCreateCommand command);
}
