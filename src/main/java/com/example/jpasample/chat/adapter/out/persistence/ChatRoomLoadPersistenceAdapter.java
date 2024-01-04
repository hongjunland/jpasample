package com.example.jpasample.chat.adapter.out.persistence;

import com.example.jpasample.chat.application.port.out.LoadChatRoomPort;
import com.example.jpasample.chat.domain.ChatMessage;
import com.example.jpasample.chat.domain.ChatRoom;
import com.example.jpasample.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
class ChatRoomLoadPersistenceAdapter implements LoadChatRoomPort{
    private final SpringDataChatRoomRepository springDataChatRoomRepository;

    @Override
    public ChatRoom loadById(Long roomId) {
        ChatRoomJpaEntity chatRoomJpaEntity = springDataChatRoomRepository.findById(roomId)
                .orElseThrow(RuntimeException::new);
        return ChatRoom.builder()
                .roomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                .messageList(chatRoomJpaEntity.getChatMessageList()
                        .stream().map(chatMessageJpaEntity ->
                                ChatMessage.builder()
                                        .chatId(new ChatMessage.ChatId(chatMessageJpaEntity.getChatMessageId()))
                                        .content(chatMessageJpaEntity.getContent())
                                        .writer(chatMessageJpaEntity.getWriter())
                                        .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<ChatRoom> search() {
        List<ChatRoomJpaEntity> chatRoomJpaEntityList = springDataChatRoomRepository.findAll();
        return chatRoomJpaEntityList.stream().map(chatRoomJpaEntity -> ChatRoom
                        .builder()
                        .roomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                        .build())
                .collect(Collectors.toList());
    }
}
