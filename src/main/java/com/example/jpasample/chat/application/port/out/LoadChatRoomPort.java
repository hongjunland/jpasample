package com.example.jpasample.chat.application.port.out;

import com.example.jpasample.chat.domain.ChatRoom;

public interface LoadChatRoomPort {
    ChatRoom loadById(Long roomId);
}
