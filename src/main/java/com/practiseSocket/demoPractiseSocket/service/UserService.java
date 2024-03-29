package com.practiseSocket.demoPractiseSocket.service;

import com.practiseSocket.demoPractiseSocket.model.Status;
import com.practiseSocket.demoPractiseSocket.model.User;
import com.practiseSocket.demoPractiseSocket.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;

    @Transactional
    public void save(User user) {
        user.setStatus(Status.ONLINE);
        repo.save(user);
    }


    public void disconnect(User user) {
        repo.findById(user.getId())
                .ifPresent(user1 ->
                        user1.setStatus(Status.OFFLINE));
    }

    public List<User> findConnectedUsers() {
        return repo.findAllByStatus(Status.ONLINE);
    }
}
