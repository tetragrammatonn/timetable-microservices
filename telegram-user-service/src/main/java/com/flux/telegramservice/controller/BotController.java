package com.flux.telegramservice.controller;

import com.flux.telegramservice.botconfiguration.Bot;
import com.flux.telegramservice.controller.generator.CommandGenerator;
import com.flux.telegramservice.controller.generator.impl.AddDaysCommandGenerator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class BotController extends Bot {

    private final Map<String, CommandGenerator> commands = new HashMap<>();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        send(update);
    }

    @SneakyThrows
    public void send(Update update) {

        log.info("\n###################--> Message send to: " + update.getMessage().getChat().getFirstName() + " " + update.getMessage().getChat().getLastName() + " <-- ###################\n" +
                "###################--> Message text   : " + update.getMessage().getText() + "          <-- ###################");

        if (update.getMessage() != null) {
            String command = update.getMessage().getText();
            CommandGenerator commandGenerator = commands.get(command);

            if (commandGenerator != null) {
                execute(commandGenerator.generateCommand(update));
            }
        } else {
            String command = update.getCallbackQuery().getData();
            AddDaysCommandGenerator addDaysCommandGenerator = new AddDaysCommandGenerator() {

                @Override
                public String getInputCommand() {
                    return command;
                }
            };

            if (addDaysCommandGenerator.getCommandsList().contains(command)) {
                execute(addDaysCommandGenerator.generateCommand(update));
            }
        }

    }

    public void register(String code, CommandGenerator generator) {
        commands.put(code, generator);
    }
}
