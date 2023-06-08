package com.example.postsystemforfather.service.telegram;

import com.example.postsystemforfather.model.ProductsModel;
import com.example.postsystemforfather.service.ProductService;
import com.example.postsystemforfather.service.telegram.component.UpdateReceiver;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String bot_name;
    @Value("${bot.token}")
    private String token;
    private final UpdateReceiver updateReceiver;
    private final ProductService productService;

    public TelegramBot(UpdateReceiver updateReceiver, ProductService productService) {
        this.updateReceiver = updateReceiver;
        this.productService = productService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        List<PartialBotApiMethod<? extends Serializable>> messagesToSend = updateReceiver.handle(update);
        if (messagesToSend != null && !messagesToSend.isEmpty()) {
            messagesToSend.forEach(response -> {
                if (response instanceof SendMessage) {
                    executeWithExceptionCheck((SendMessage) response);
                }
                if (response instanceof GetFile) {
                    executeForDocument((GetFile) response);
                }
            });
        }
    }

    private void executeForDocument(GetFile getFile) {
        try {
            File file = execute(getFile);
            URL url = new URL(file.getFileUrl(token));
            XSSFWorkbook hs = new XSSFWorkbook(url.openStream());
            XSSFSheet sheet = hs.getSheetAt(0);
            LinkedList<ProductsModel> list = new LinkedList<>();
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);
                list.add(new ProductsModel(row.getCell(0).toString()));
                System.out.println("set to list");
            }
        productService.createOrUpdate(list);
        } catch (TelegramApiException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeWithExceptionCheck(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e);
        }
    }

    @Override
    public String getBotUsername() {
        return bot_name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

//    private void startBot(long chatId, long user_id, String userName) {
//        SendMessage message = new SendMessage();
//
//        message.setChatId(chatId);
////        ReplyKeyboardMarkup keyboardRows = createKeyboardRows(user_id);
//        LinkedList<KeyboardRow> keyboardList = new LinkedList<>();
//        Optional<Employee> user = employeeRepo.findByUserId(user_id);
//        KeyboardRow keyboardButtons = new KeyboardRow();
//        KeyboardButton keyboardButton = new KeyboardButton();
//        keyboardButton.setText("Mahsulotlar kiritish");
//        keyboardButtons.add(keyboardButton);
//        keyboardList.add(keyboardButtons);
//        keyboardButtons = new KeyboardRow();
//        keyboardButton = new KeyboardButton();
//        keyboardButton.setText("Zakazlar qabul qilish");
//        keyboardButtons.add(keyboardButton);
//        keyboardList.add(keyboardButtons);
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        replyKeyboardMarkup.setKeyboard(keyboardList);
//        message.setReplyMarkup(replyKeyboardMarkup);
//        try {
//            execute(message);
//            log.info("Reply sent");
//        } catch (TelegramApiException e) {
//            log.error(e.getMessage());
//        }
//    }
}
