package com.example.share.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    private String senderNickname;
    private String recipientNickname;
    private LocalDateTime sentAt;

//    @ManyToOne
//    @JoinColumn(name = "room_id")
//    private ChatRoom room;

    private Long roomId;

    public ChatMessage(String content, Long senderId, String recipientNickname, Long roomId) {
        this.content = content;
        this.senderId = senderId;
        this.recipientNickname = recipientNickname;
        this.sentAt = LocalDateTime.now();
        this.roomId = roomId;
    }



}
