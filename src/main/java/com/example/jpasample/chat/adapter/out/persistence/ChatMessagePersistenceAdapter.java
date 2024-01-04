package com.example.jpasample.chat.adapter.out.persistence;

import com.example.jpasample.chat.application.port.out.CreateChatMessagePort;
import com.example.jpasample.chat.domain.ChatMessage;
import com.example.jpasample.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
class ChatMessagePersistenceAdapter implements CreateChatMessagePort {
    private final SpringDataChatRoomRepository springDataChatRoomRepository;

    @Override
    @Transactional
    public Long createChatMessage(ChatMessage chatMessage) {
        ChatRoomJpaEntity chatRoomJpaEntity = springDataChatRoomRepository.findById(chatMessage.getChatRoomId().value())
                .orElseThrow(RuntimeException::new);
        ChatMessageJpaEntity chatMessageJpaEntity = ChatMessageJpaEntity.builder()
                .chatRoom(chatRoomJpaEntity)
                .content(chatMessage.getContent())
                .writer(chatMessage.getWriter())
                .build();
        chatRoomJpaEntity.createMessage(chatMessageJpaEntity);
        springDataChatRoomRepository.save(chatRoomJpaEntity);
        return chatMessageJpaEntity.getChatMessageId();
    }
}
