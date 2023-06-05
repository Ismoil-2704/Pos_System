package com.example.postsystemforfather.service.telegram;

import com.example.postsystemforfather.entity.BotSteps;
import com.example.postsystemforfather.service.telegram.Enum.State;
import com.example.postsystemforfather.service.telegram.component.Handler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.io.Serializable;
import java.util.List;

@Component
public class CreateProduct implements Handler {
    @Override
    public List<PartialBotApiMethod<? extends Serializable>> handle(BotSteps user, String message,Long user_id) {

        return null;
    }

    @Override
    public State operatedBotState() {
        return State.CREATE_PRODUCT;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return null;
    }
}
