package com.example.jpasample.chat.application.port.in.query;

import lombok.Builder;

@Builder
public record ChatRoomQuery(Long id, int page, int size) {
}
