package com.example.jpasample.chat.application.port.out;

import com.example.jpasample.chat.domain.ChatRoom;

import java.util.List;

public interface LoadChatRoomPort {
    ChatRoom loadById(Long roomId);
    List<ChatRoom> search();
}
