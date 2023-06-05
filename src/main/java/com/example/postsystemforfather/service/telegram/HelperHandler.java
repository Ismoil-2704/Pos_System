package com.example.postsystemforfather.service.telegram;

import com.example.postsystemforfather.entity.BotSteps;
import com.example.postsystemforfather.repository.BotStepRepo;
import com.example.postsystemforfather.service.telegram.Enum.State;
import com.example.postsystemforfather.service.telegram.component.BotCommands;
import com.example.postsystemforfather.service.telegram.component.Handler;
import com.example.postsystemforfather.utils.TelegramUtil;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class HelperHandler implements Handler {

   private final BotStepRepo botStepRepo;

    public HelperHandler(BotStepRepo botStepRepo) {
        this.botStepRepo = botStepRepo;
    }

    @Override
    public List<PartialBotApiMethod<? extends Serializable>> handle(BotSteps user, String message, Long user_id) {
        LinkedList<PartialBotApiMethod<? extends Serializable>> list = new LinkedList<>();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        LinkedList<KeyboardRow> keyBoard = new LinkedList<>();
        KeyboardRow row = new KeyboardRow();
        SendMessage sendMessage = new SendMessage();
        if (message.equals("Maxsulot qo'shish")) {
           sendMessage = TelegramUtil.createMessageTemplate(user);
           sendMessage.setText(BotCommands.create_product);
           user.setBotState(State.CREATE_PRODUCT);
        }else if (message.equals("Zakaz qabul qilish")){
            sendMessage = TelegramUtil.createMessageTemplate(user);
            sendMessage.setText(BotCommands.start_selling);
            user.setBotState(State.CREAT_SELLING);
        }
        row.add("Ortga Qaytish");
        keyBoard.add(row);
        keyboardMarkup.setKeyboard(keyBoard);
        sendMessage.setReplyMarkup(keyboardMarkup);
        botStepRepo.save(user);
        list.add(sendMessage);
        return list;
    }

    @Override
    public State operatedBotState() {
        return State.CHECK_SEL_OR_CREATE_PROD;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return Collections.emptyList();
    }
}
