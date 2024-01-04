package com.example.jpasample.chat.application.service;

import com.example.jpasample.chat.application.port.in.ChatMessageCreateUseCase;
import com.example.jpasample.chat.application.port.in.command.ChatMessageCreateCommand;
import com.example.jpasample.chat.application.port.out.CreateChatMessagePort;
import com.example.jpasample.chat.domain.ChatMessage;
import com.example.jpasample.chat.domain.ChatRoom;
import com.example.jpasample.common.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
class CreateChatMessageService implements ChatMessageCreateUseCase {
    private final CreateChatMessagePort createChatMessagePort;

    @Override
    public Long createChatMessage(ChatMessageCreateCommand command) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoomId(new ChatRoom.RoomId(command.roomId()))
                .content(command.content())
                .writer(command.from())
                .build();
        return createChatMessagePort.createChatMessage(chatMessage);
    }
}
