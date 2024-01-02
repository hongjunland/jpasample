package com.example.jpasample.chat.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SpringDataChatMessageRepository extends JpaRepository<ChatMessageJpaEntity, Long> {
    Optional<ChatRoomJpaEntity> findByChatRoomChatRoomId(Long id);
}
