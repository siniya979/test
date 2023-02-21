package com.example.share.dto;

import com.sparta.soomtut.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatResponseDto {

    private String senderNickname;
    private String recipientNickname;
    private String message;
    private LocalDateTime sentAt;

    private ChatResponseDto(String senderNickname, String recipientNickname, String message, LocalDateTime sentAt) {
        this.senderNickname = senderNickname;
        this.recipientNickname = recipientNickname;
        this.message = message;
        this.sentAt = sentAt;
    }

    public static ChatRequestDto of(ChatMessage chatMessage){
        return new ChatRequestDto(
                chatMessage.getSenderId()
        );
    }

}
