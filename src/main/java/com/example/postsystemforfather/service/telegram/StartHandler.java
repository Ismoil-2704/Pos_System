package com.example.postsystemforfather.service.telegram;

import com.example.postsystemforfather.entity.BotSteps;
import com.example.postsystemforfather.entity.Employee;
import com.example.postsystemforfather.repository.BotStepRepo;
import com.example.postsystemforfather.repository.EmployeeRepo;
import com.example.postsystemforfather.service.telegram.Enum.State;
import com.example.postsystemforfather.service.telegram.component.Handler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.Serializable;
import java.util.*;

@Component
public class StartHandler implements Handler {

    private final EmployeeRepo employeeRepo;
    private final BotStepRepo botStepRepo;

    public StartHandler(EmployeeRepo employeeRepo, BotStepRepo botStepRepo) {
        this.employeeRepo = employeeRepo;
        this.botStepRepo = botStepRepo;
    }

    @Override
    public List<PartialBotApiMethod<? extends Serializable>> handle(BotSteps user, String message,Long user_id) {
        List<PartialBotApiMethod<? extends Serializable>> list = new ArrayList<>();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        LinkedList<KeyboardRow> keyBoard = new LinkedList<>();
        KeyboardRow row = new KeyboardRow();
        Optional<Employee> byUserId = employeeRepo.findByUserId(user_id);
        if (byUserId.isPresent()) {
            row.add("Maxsulot qo'shish");
            keyBoard.add(row);
            keyboardMarkup.setKeyboard(keyBoard);
            row = new KeyboardRow();
        }
        row.add("Zakaz qabul qilish");
        keyBoard.add(row);
        keyboardMarkup.setKeyboard(keyBoard);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(user.getChatId()));
        sendMessage.enableMarkdown(true);
        sendMessage.setText("Xolatni tanlang!");
        sendMessage.setReplyMarkup(keyboardMarkup);
        list.add(sendMessage);
        user.setBotState(State.CHECK_SEL_OR_CREATE_PROD);
        botStepRepo.save(user);
        return list;
    }


    @Override
    public State operatedBotState() {
        return State.START;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return Collections.emptyList();
    }
}
