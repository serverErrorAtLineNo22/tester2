package com.practiseSocket.demoPractiseSocket.service;

import com.practiseSocket.demoPractiseSocket.model.ChatRoom;
import com.practiseSocket.demoPractiseSocket.repository.ChatRoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepo roomRepo;

    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ) {
        return roomRepo
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExists) {
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }

                    return  Optional.empty();
                });
    }

    public String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        roomRepo.save(senderRecipient);
        roomRepo.save(recipientSender);

        return chatId;
    }
}
