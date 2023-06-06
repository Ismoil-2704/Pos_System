package com.example.postsystemforfather.service.telegram.component;

import com.example.postsystemforfather.entity.BotSteps;
import com.example.postsystemforfather.repository.BotStepRepo;
import com.example.postsystemforfather.service.telegram.Enum.State;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateReceiver {

    private final List<Handler> handlers;
    private final BotStepRepo botStepRepo;

    public UpdateReceiver(List<Handler> handlers, BotStepRepo botStepRepo) {
        this.handlers = handlers;
        this.botStepRepo = botStepRepo;
    }

    public List<PartialBotApiMethod<? extends Serializable>> handle(Update update) {
        try {
            if (update.getMessage().hasDocument()){
                final Message message = update.getMessage();
                final long user_id = message.getFrom().getId();
                Long id = message.getChatId();
                String firstName = message.getChat().getFirstName();
                String text = message.getText();
                Document document = message.getDocument();
                Optional<BotSteps> byChatId = botStepRepo.findByChatId(id);
                BotSteps user = byChatId.orElseThrow();
                return getHandlerByState(user.getBotState()).handle(user, text, user_id,document);
            }
            if (isMessageWithText(update)) {
                BotSteps user = new BotSteps();
                final Message message = update.getMessage();
                final long user_id = message.getFrom().getId();
                Long id = message.getChatId();
                String firstName = message.getChat().getFirstName();
                String text = message.getText();
                Optional<BotSteps> byChatId = botStepRepo.findByChatId(id);
                if (text.equals("/start") || text.equals("Ortga")) {
                    if (byChatId.isPresent()) {
                        user = byChatId.get();
                        user.setBotState(State.START);
                    } else {
                        user.setChatId(id);
                        user.setName(firstName);
                    }
                    botStepRepo.save(user);
                }else {
                    user = byChatId.orElseGet(() -> new BotSteps(id,firstName));
                }
                return getHandlerByState(user.getBotState()).handle(user, text, user_id,new Document());
            } else if (update.hasCallbackQuery()) {
                final CallbackQuery callbackQuery = update.getCallbackQuery();
                final long chatId = callbackQuery.getFrom().getId();
                String firstName = callbackQuery.getMessage().getChat().getFirstName();
                final BotSteps user = botStepRepo.findByChatId(chatId)
                        .orElseGet(() -> botStepRepo.save(new BotSteps(chatId, firstName)));
                return getHandlerByCallBackQuery(callbackQuery.getData()).handle(user, callbackQuery.getData(), 1L,new Document());
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
