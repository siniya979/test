package com.example.share.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="room_id")
    private Long id;

    private Long senderId;
    private Long recipientId;

    private ChatRoom(Long senderId, Long recipientId) {
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public static ChatRoom of(Long senderId, Long recipientId){
        return new ChatRoom(senderId, recipientId);
    }


}
