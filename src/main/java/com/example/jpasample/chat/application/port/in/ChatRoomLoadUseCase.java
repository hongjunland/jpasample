package com.example.jpasample.chat.application.port.in;

import com.example.jpasample.chat.adapter.in.web.dto.ChatRoomItemResponse;
import com.example.jpasample.chat.adapter.in.web.dto.ChatRoomListReadResponse;
import com.example.jpasample.chat.adapter.in.web.dto.ChatRoomResponse;
import com.example.jpasample.chat.application.port.in.query.ChatRoomListQuery;
import com.example.jpasample.chat.application.port.in.query.ChatRoomQuery;

import java.util.List;

public interface ChatRoomLoadUseCase {
    ChatRoomResponse getChatRoomById(ChatRoomQuery chatRoomQuery);
    ChatRoomListReadResponse getChatRoomList(ChatRoomListQuery query);
}
