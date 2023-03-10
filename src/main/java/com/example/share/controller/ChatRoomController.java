package com.example.share.controller;

import com.sparta.soomtut.chat.dto.ChatRoomResponse;
import com.sparta.soomtut.chat.service.ChatRoomService;
import com.sparta.soomtut.chat.service.ChatService;
import com.sparta.soomtut.dto.request.PageRequestDto;
import com.sparta.soomtut.util.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat_room")
public class ChatRoomController {

    private final ChatService chatService;
    private final ChatRoomService chatRoomService;

    // 채팅방 개설
    @PostMapping
    public void createRoom(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String recipientNickname) {
        chatRoomService.createRoom(userDetails.getMemberId(), recipientNickname);
    }

    // 채팅방 가져오기
    @PostMapping
    public ChatRoomResponse getMyChatRoom(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String recipientNickname) {
        chatRoomService.getMyChatRoom(userDetails.getMemberId(), recipientNickname);
    }

    // 나의 채팅방 조회
    @GetMapping
    public ResponseEntity<?> getMyChatRooms(@AuthenticationPrincipal UserDetailsImpl userDetails, @ModelAttribute PageRequestDto pageable) {
        return chatRoomService.getMyChatRooms(userDetails.getMemberId(),pageable.toPageable());
        // return ToResponse.of(data, SuccessCode.MESSGE_OK);
    }

}
