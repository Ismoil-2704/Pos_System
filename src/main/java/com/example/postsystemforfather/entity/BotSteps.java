package com.example.postsystemforfather.entity;

import com.example.postsystemforfather.service.telegram.Enum.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BotSteps extends BaseEntity{

    @Column(name = "chat_id", unique = true, nullable = false)
    private Long chatId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "bot_state", nullable = false)
    private State botState;

    public BotSteps(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
        this.botState = State.START;
    }
}
