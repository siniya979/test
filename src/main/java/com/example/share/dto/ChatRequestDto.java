package com.example.share.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class ChatRequestDto {

    private Long senderId;
    private String recipientNickname;
    private Long roomId;
    private String message;


}
