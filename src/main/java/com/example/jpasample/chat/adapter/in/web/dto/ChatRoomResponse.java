package com.example.jpasample.chat.adapter.in.web.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ChatRoomResponse(Long roomId, List<ChatMessageResponse> messageList) {
}
