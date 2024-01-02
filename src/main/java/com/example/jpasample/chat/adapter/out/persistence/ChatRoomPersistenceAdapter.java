package com.example.jpasample.chat.adapter.out.persistence;

import com.example.jpasample.chat.application.port.in.ChatRoomCreateUseCase;
import com.example.jpasample.chat.application.port.in.command.ChatRoomCreateCommand;
import com.example.jpasample.chat.application.port.out.CreateChatMessagePort;
import com.example.jpasample.chat.application.port.out.CreateChatRoomPort;
import com.example.jpasample.chat.domain.ChatMessage;
import com.example.jpasample.chat.domain.ChatRoom;
import com.example.jpasample.common.annotation.PersistenceAdapter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class ChatRoomPersistenceAdapter implements CreateChatRoomPort, CreateChatMessagePort {
    private final SpringDataChatRoomRepository springDataChatRoomRepository;

    @Transactional
    @Override
    public boolean createChatRoom(ChatRoom chatRoom) {
        ChatRoomJpaEntity chatRoomJpaEntity = ChatRoomJpaEntity.builder()
                .build();
        springDataChatRoomRepository.save(chatRoomJpaEntity);
        return true;
    }

    @Transactional
    @Override
    public boolean createChatMessage(ChatMessage chatMessage) {
        ChatRoomJpaEntity chatRoomJpaEntity = springDataChatRoomRepository.findById(chatMessage.getChatRoomId().value())
                .orElseThrow(RuntimeException::new);
        ChatMessageJpaEntity chatMessageJpaEntity = ChatMessageJpaEntity.builder()
                .chatRoom(chatRoomJpaEntity)
                .content(chatMessage.getContent())
                .writer(chatMessage.getWriter())
                .build();
        chatRoomJpaEntity.createMessage(chatMessageJpaEntity);
        springDataChatRoomRepository.save(chatRoomJpaEntity);
        return true;
    }
}
