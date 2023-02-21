package com.example.share.controller;

import com.sparta.soomtut.chat.dto.ChatRequestDto;
import com.sparta.soomtut.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatBrokerController {

    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    // 메시지를 브로커에게 보내기.
    // convertAndSendToUser 메서드는 대상 사용자에게 메시지를 보내기 위해 /user/{username}와 같은 형식의 대상 URL을 사용
    // /user/subscribe/{recipientId}
    @MessageMapping("/message")
    public void sendMessage(@Payload ChatRequestDto chatRequest) {
        chatService.save(chatRequest); // db 저장
        simpMessagingTemplate.convertAndSendToUser(
                "/subscribe/" + chatRequest.getRecipientNickname(),
                chatRequest.getMessage(),
                createHeaders(chatRequest.getRoomId()));
    }

    // 메시지 헤더에 채팅방번호 넣어주기
    private MessageHeaders createHeaders(Long roomId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
        headerAccessor.setNativeHeader("room-id", roomId.toString());
        return headerAccessor.getMessageHeaders();
    }

}

