package com.practiseSocket.demoPractiseSocket.repository;

import com.practiseSocket.demoPractiseSocket.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepo extends JpaRepository<ChatMessage,Long> {

   List<ChatMessage> findByChatId(String chatId);
}
