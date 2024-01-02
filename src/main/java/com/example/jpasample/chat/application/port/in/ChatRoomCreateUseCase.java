package com.example.jpasample.chat.application.port.in;

import com.example.jpasample.chat.application.port.in.command.ChatRoomCreateCommand;

public interface ChatRoomCreateUseCase {
    boolean createChatRoom(ChatRoomCreateCommand command);
}
