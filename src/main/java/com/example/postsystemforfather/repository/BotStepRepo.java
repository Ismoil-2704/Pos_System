package com.example.postsystemforfather.repository;

import com.example.postsystemforfather.entity.BotSteps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BotStepRepo extends JpaRepository<BotSteps,Long> {
    Optional<BotSteps> findByChatId(Long chatId);
}
