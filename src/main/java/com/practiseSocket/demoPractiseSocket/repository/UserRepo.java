package com.practiseSocket.demoPractiseSocket.repository;

import com.practiseSocket.demoPractiseSocket.model.Status;
import com.practiseSocket.demoPractiseSocket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    List<User> findAllByStatus(Status status);

    Optional<User> findOneByEmail(String email);
}
