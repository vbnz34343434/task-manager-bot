package ru.liga.taskmanagerbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.liga.taskmanagerbot.bot.Bot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TaskManagerBotApplicationTests {


	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertNotNull(applicationContext.getBean(Bot.class));
	}

}
