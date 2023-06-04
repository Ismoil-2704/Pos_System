package com.example.postsystemforfather.service;

import com.example.postsystemforfather.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedList;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = 0;
        long userId = 0;
        String userName = null;
        String receivedMessage;

        if (update.hasMessage()){
            receivedMessage = update.getMessage().getText();
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();
            userId = update.getMessage().getFrom().getId();

            if (update.getMessage().hasText()){
                botAnswer(receivedMessage,chatId,userName);
            }
        }else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            botAnswer(receivedMessage, chatId, userName);
        }
    }



    @Override
    public String getBotToken(){
        return botConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBot_name();
    }

    private void botAnswer(String receivedMessage,long chatId, String userName){
        switch (receivedMessage){
            case "/start":
                startBot(chatId,userName);
                break;
            case "/help":
                sendHelpText(chatId,"hello");
            default:break;
        }
    }

    private void startBot(long chatId, String userName) {
        LinkedList<KeyboardRow> keyboardList = new LinkedList<>();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello, "+ userName +" ! Welcome to the Our Magazine Bot");

        KeyboardRow keyboardButtons = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("Mahsulotlar kiritish");
        keyboardButtons.add(keyboardButton);
        keyboardList.add(keyboardButtons);
        keyboardButtons = new KeyboardRow();
        keyboardButton = new KeyboardButton();
        keyboardButton.setText("Zakazlar qabul qilish");
        keyboardButtons.add(keyboardButton);
        keyboardList.add(keyboardButtons);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardList);

        message.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }

    private void sendHelpText(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }
}
