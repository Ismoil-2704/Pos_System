package com.example.postsystemforfather.service.telegram;

import com.example.postsystemforfather.entity.BotSteps;
import com.example.postsystemforfather.service.telegram.Enum.State;
import com.example.postsystemforfather.service.telegram.component.Handler;
import com.example.postsystemforfather.utils.TelegramUtil;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Component
public class CreateProduct implements Handler {
    @Override
    public List<PartialBotApiMethod<? extends Serializable>> handle(BotSteps user, String message, Long user_id, Document document) {
        LinkedList<PartialBotApiMethod<? extends Serializable>> list = new LinkedList<>();
        GetFile getFile = new GetFile();
        getFile.setFileId(document.getFileId());
        list.add(getFile);
        return list;
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
