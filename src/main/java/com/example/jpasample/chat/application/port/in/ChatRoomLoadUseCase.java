package com.example.jpasample.chat.application.port.in;

import com.example.jpasample.chat.adapter.in.web.dto.ChatMessageResponse;
import com.example.jpasample.chat.adapter.in.web.dto.ChatRoomItemResponse;
import com.example.jpasample.chat.adapter.in.web.dto.ChatRoomResponse;
import com.example.jpasample.chat.application.port.in.command.ChatRoomQuery;

import java.util.List;

public interface ChatRoomLoadUseCase {
    ChatRoomResponse getChatRoomById(ChatRoomQuery chatRoomQuery);
    List<ChatRoomItemResponse> getChatRoomList();
}
