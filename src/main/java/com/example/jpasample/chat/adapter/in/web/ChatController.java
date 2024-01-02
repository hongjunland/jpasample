package com.example.jpasample.chat.adapter.in.web;

import com.example.jpasample.chat.adapter.in.web.dto.ChatMessageRequest;
import com.example.jpasample.chat.application.port.in.ChatMessageCreateUseCase;
import com.example.jpasample.chat.application.port.in.command.ChatMessageCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
class ChatController {
    private final ChatMessageCreateUseCase chatMessageCreateUseCase;
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessageRequest sendMessage(@Payload ChatMessageRequest chatMessage) {
        ChatMessageCreateCommand chatMessageCreateCommand = ChatMessageCreateCommand.builder()
                .content(chatMessage.text())
                .from(chatMessage.from())
                .roomId(chatMessage.roomId())
                .build();
        chatMessageCreateUseCase.createChatMessage(chatMessageCreateCommand);
        return chatMessage;
    }
}
