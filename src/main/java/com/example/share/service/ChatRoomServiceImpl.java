package com.example.share.service;

import com.sparta.soomtut.chat.dto.ChatRoomResponse;
import com.sparta.soomtut.chat.entity.ChatRoom;
import com.sparta.soomtut.chat.repository.ChatRoomRepository;
import com.sparta.soomtut.entity.Member;
import com.sparta.soomtut.exception.ErrorCode;
import com.sparta.soomtut.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{

    private final ChatRoomRepository chatRoomRepository;
    private final MemberService memberService;

    // 새로운 채팅방 생성
    @Override
    public void createRoom(Long memberId, String recipientNickname) {
        Member recipient = memberService.getMemberByNickname(recipientNickname);
        if(chatRoomRepository.existsBySenderIdAndRecipientId(memberId, recipient.getId())){
            throw new IllegalArgumentException(ErrorCode.DUPLICATED_CHATTING.getMessage());
        }

        ChatRoom chatRoom = ChatRoom.of(memberId,recipient.getId());
        chatRoomRepository.save(chatRoom);

    }

    @Override
    public ChatRoomResponse getMyChatRoom(Long memberId, String recipientNickname) {



        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChatRoomResponse> getMyChatRooms(Long memberId, Pageable pageable) {
        Page<ChatRoom> chatRoomList = getAllMyChatRooms(memberId,pageable);


        return chatRoomList.map(room -> new ChatRoomResponse(room));
    }


    // 지원 함수
    @Override
    public Page<ChatRoom> getAllMyChatRooms(Long memberId, Pageable pageable) {
        return chatRoomRepository.findAllBySenderIdAndRecipientId(memberId, memberId, pageable);
    }


}
