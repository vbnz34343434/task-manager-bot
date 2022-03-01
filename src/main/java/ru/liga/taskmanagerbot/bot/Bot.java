package ru.liga.taskmanagerbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.taskmanagerbot.exception.UserNotFoundException;
import ru.liga.taskmanagerbot.service.UserService;

@Component
public class Bot extends TelegramLongPollingBot {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(Bot.class);


    @Value("${bot.token}")
    String botToken;

    public Bot(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getBotUsername() {
        return "dmelnik_bot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            String response;
            try {
                response = userService.getUserByIdSync(text);
            } catch (UserNotFoundException e) {
                response = e.getMessage();
            }

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText("response:\n".concat(response));
            logger.info(String.valueOf(message));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
