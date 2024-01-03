package com.example.jpasample.chat.application.service;

import com.example.jpasample.chat.adapter.in.web.dto.ChatMessageResponse;
import com.example.jpasample.chat.adapter.in.web.dto.ChatRoomItemResponse;
import com.example.jpasample.chat.adapter.in.web.dto.ChatRoomResponse;
import com.example.jpasample.chat.application.port.in.ChatRoomLoadUseCase;
import com.example.jpasample.chat.application.port.in.command.ChatRoomQuery;
import com.example.jpasample.chat.application.port.out.LoadChatRoomPort;
import com.example.jpasample.chat.domain.ChatRoom;
import com.example.jpasample.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
class LoadChatRoomService implements ChatRoomLoadUseCase {
    private final LoadChatRoomPort loadChatRoomPort;
    @Override
    public ChatRoomResponse getChatRoomById(ChatRoomQuery chatRoomQuery) {
        ChatRoom chatRoom = loadChatRoomPort.loadById(chatRoomQuery.id());

        return ChatRoomResponse.builder()
                .roomId(chatRoom.getRoomId().value())
                .messageList(chatRoom.getMessageList()
                    .stream().map((chatMessage ->
                        ChatMessageResponse.builder()
                            .id(chatMessage.getChatId().value())
                            .content(chatMessage.getContent())
                            .writer(chatMessage.getWriter())
                            .build()
                    )).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<ChatRoomItemResponse> getChatRoomList() {
        List<ChatRoom> chatRoomList = loadChatRoomPort.search();
        return chatRoomList.stream().map(chatRoom -> ChatRoomItemResponse.builder()
                        .roomId(chatRoom.getRoomId().value())
                        .build())
                .collect(Collectors.toList());
    }
}
