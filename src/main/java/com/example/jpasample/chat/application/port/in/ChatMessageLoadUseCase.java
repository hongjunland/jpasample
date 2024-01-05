package com.example.jpasample.chat.application.port.in;

import com.example.jpasample.chat.adapter.in.web.dto.ChatMessageResponse;
import com.example.jpasample.chat.application.port.in.command.ChatMessageCreateCommand;
import com.example.jpasample.chat.application.port.in.query.ChatMessageListQuery;

import java.util.List;

public interface ChatMessageLoadUseCase {
    List<ChatMessageResponse> getChatMessageList(ChatMessageListQuery command);
}
