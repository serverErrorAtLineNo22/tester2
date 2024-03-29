package com.practiseSocket.demoPractiseSocket.service;

import com.practiseSocket.demoPractiseSocket.model.ChatMessage;
import com.practiseSocket.demoPractiseSocket.repository.ChatMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;
    private final ChatRoomService chatRoomService;


    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId()
                , chatMessage.getRecipientId(), true).orElseThrow();
        chatMessage.setChatId(chatId);
        chatMessageRepo.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(chatMessageRepo::findByChatId).orElse(new ArrayList<>());
    }
}
