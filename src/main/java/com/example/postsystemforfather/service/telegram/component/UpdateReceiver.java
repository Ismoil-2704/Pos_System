package com.example.postsystemforfather.service.telegram.component;

import com.example.postsystemforfather.entity.BotSteps;
import com.example.postsystemforfather.repository.BotStepRepo;
import com.example.postsystemforfather.service.telegram.Enum.State;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
@Component
public class UpdateReceiver {

    private final List<Handler> handlers;
    private final BotStepRepo botStepRepo;

    public UpdateReceiver(List<Handler> handlers, BotStepRepo botStepRepo) {
        this.handlers = handlers;
        this.botStepRepo = botStepRepo;
    }
    public List<PartialBotApiMethod<? extends Serializable>> handle(Update update){
        try {
            if (isMessageWithText(update)){
                final Message message = update.getMessage();
                final long user_id = message.getFrom().getId();
                Long id = message.getChatId();
                String firstName = message.getChat().getFirstName();
                final BotSteps user = botStepRepo.findByChatId(id)
                        .orElseGet(() -> botStepRepo.save(new BotSteps(id, firstName)));
                return getHandlerByState(user.getBotState()).handle(user, message.getText(),user_id);
            }else if (update.hasCallbackQuery()){
                final CallbackQuery callbackQuery = update.getCallbackQuery();
                final long chatId = callbackQuery.getFrom().getId();
                String firstName = callbackQuery.getMessage().getChat().getFirstName();
                final BotSteps user = botStepRepo.findByChatId(chatId)
                        .orElseGet(() -> botStepRepo.save(new BotSteps(chatId, firstName)));
                return getHandlerByCallBackQuery(callbackQuery.getData()).handle(user, callbackQuery.getData(),1L);
            }
            throw new UnsupportedOperationException();
        } catch (UnsupportedOperationException e) {
            return Collections.emptyList();
        }
    }
    private Handler getHandlerByState(State state) {
        return handlers.stream()
                .filter(h -> h.operatedBotState() != null)
                .filter(h -> h.operatedBotState().equals(state))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }
    private Handler getHandlerByCallBackQuery(String query) {
        return handlers.stream()
                .filter(h -> h.operatedCallBackQuery().stream()
                        .anyMatch(query::startsWith))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }
    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }
}
