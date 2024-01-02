package com.example.jpasample.chat.application.service;

import com.example.jpasample.chat.application.port.in.ChatRoomCreateUseCase;
import com.example.jpasample.chat.application.port.in.command.ChatRoomCreateCommand;
import com.example.jpasample.chat.application.port.out.CreateChatRoomPort;
import com.example.jpasample.chat.domain.ChatRoom;
import com.example.jpasample.common.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
class CreateChatRoomService implements ChatRoomCreateUseCase {
    private final CreateChatRoomPort createChatRoomPort;
    @Override
    public boolean createChatRoom(ChatRoomCreateCommand command) {
        ChatRoom chatRoom = ChatRoom.builder()
                .build();
        return createChatRoomPort.createChatRoom(chatRoom);
    }

}
