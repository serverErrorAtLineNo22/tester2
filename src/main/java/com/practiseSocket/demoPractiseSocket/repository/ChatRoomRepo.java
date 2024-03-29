package com.practiseSocket.demoPractiseSocket.repository;

import com.practiseSocket.demoPractiseSocket.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepo extends JpaRepository<ChatRoom,Long> {

   Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
