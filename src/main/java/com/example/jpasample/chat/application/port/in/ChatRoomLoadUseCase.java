package com.example.jpasample.chat.application.port.in;

import com.example.jpasample.chat.adapter.in.web.dto.ChatMessageResponse;
import com.example.jpasample.chat.adapter.in.web.dto.ChatRoomResponse;
import com.example.jpasample.chat.application.port.in.command.ChatRoomQuery;

public interface ChatRoomLoadUseCase {
    ChatRoomResponse getChatRoomById(ChatRoomQuery chatRoomQuery);
}
