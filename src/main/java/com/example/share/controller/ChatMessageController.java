package com.example.share.controller;

import com.sparta.soomtut.chat.repository.ChatRoomRepository;
import com.sparta.soomtut.entity.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ChatMessageController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    // 프론트에서 roomId를 요청해서 받기 위한 메서드
    @MessageMapping("/roomId")
    @SendToUser("/queue/roomId")
    public String getRoomId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ChatRoom chatRoom = chatRoomRepository.findByUsername(username);
        return chatRoom.getId();
    }


}
