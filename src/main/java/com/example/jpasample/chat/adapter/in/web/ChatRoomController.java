package com.example.jpasample.chat.adapter.in.web;


import com.example.jpasample.chat.application.port.in.ChatRoomCreateUseCase;
import com.example.jpasample.chat.application.port.in.command.ChatRoomCreateCommand;
import com.example.jpasample.common.response.SuccessApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
class ChatRoomController {
    private final ChatRoomCreateUseCase chatRoomCreateUseCase;

    @PostMapping
    public SuccessApiResponse<?> createChatRoom(){
        ChatRoomCreateCommand chatRoomCreateCommand = ChatRoomCreateCommand.builder()
                .build();
        chatRoomCreateUseCase.createChatRoom(chatRoomCreateCommand);
        return SuccessApiResponse.of();
    }
}
