package com.example.jpasample.chat.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataChatRoomRepository extends JpaRepository<ChatRoomJpaEntity, Long> {
}
