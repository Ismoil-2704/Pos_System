package com.example.postsystemforfather.service.telegram;

import com.example.postsystemforfather.entity.BotSteps;
import com.example.postsystemforfather.service.telegram.Enum.State;
import com.example.postsystemforfather.service.telegram.component.Handler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Document;

import java.io.Serializable;
import java.util.List;

@Component
public class CreateSel implements Handler {
    @Override
    public List<PartialBotApiMethod<? extends Serializable>> handle(BotSteps user, String message, Long user_id, Document document) {
        return null;
    }

    @Override
    public State operatedBotState() {
        return null;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return null;
    }
}
