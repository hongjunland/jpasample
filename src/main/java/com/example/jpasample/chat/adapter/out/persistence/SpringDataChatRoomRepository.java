package com.example.jpasample.chat.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface SpringDataChatRoomRepository extends JpaRepository<ChatRoomJpaEntity, Long> {
    @Query("select room from ChatRoomJpaEntity room LEFT JOIN FETCH room.chatMessageList message where room.chatRoomId = :id")
    @Override
    Optional<ChatRoomJpaEntity> findById(Long id);
}
