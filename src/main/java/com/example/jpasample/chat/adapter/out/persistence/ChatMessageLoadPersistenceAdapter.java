package com.example.jpasample.chat.adapter.out.persistence;

import com.example.jpasample.chat.application.port.out.LoadChatMessagePort;
import com.example.jpasample.chat.domain.ChatMessage;
import com.example.jpasample.chat.domain.ChatRoom;
import com.example.jpasample.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
class ChatMessageLoadPersistenceAdapter implements LoadChatMessagePort {
    private final SpringDataChatRoomRepository springDataChatRoomRepository;
    private final SpringDataChatMessageRepository springDataChatMessageRepository;

    @Transactional
    @Override
    public List<ChatMessage> loadChatMessegeList(Long roomId, PageRequest pageRequest) {
        ChatRoomJpaEntity chatRoomJpaEntity = springDataChatRoomRepository.findById(roomId)
                .orElseThrow(RuntimeException::new);
        Slice<ChatMessageJpaEntity> chatMessageJpaEntitySlice = springDataChatMessageRepository.findAllByChatRoom(chatRoomJpaEntity, pageRequest);
        return chatMessageJpaEntitySlice.getContent().stream().map(
                (chatMessageJpaEntity)-> ChatMessage.builder()
                        .chatId(new ChatMessage.ChatId(chatMessageJpaEntity.getChatMessageId()))
                        .content(chatMessageJpaEntity.getContent())
                        .writer(chatMessageJpaEntity.getWriter())
                        .chatRoomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                        .build()
        ).collect(Collectors.toList());
    }
}
