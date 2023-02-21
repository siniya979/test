package com.example.share.service;

import com.sparta.soomtut.chat.dto.ChatRoomResponse;
import com.sparta.soomtut.chat.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatRoomService {
    void createRoom(Long memberId, String recipientNickname);

    Page<ChatRoomResponse> getMyChatRooms(Long memberId, Pageable pageable);
    ChatRoomResponse getMyChatRoom(Long memberId, String recipientNickname);

    Page<ChatRoom> getAllMyChatRooms(Long memberId, Pageable pageable);

}
