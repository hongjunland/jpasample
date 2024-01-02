package com.example.jpasample.chat.application.port.in;

import com.example.jpasample.chat.application.port.in.command.ChatMessageCreateCommand;

public interface ChatMessageCreateUseCase {
    boolean createChatMessage(ChatMessageCreateCommand command);
}
