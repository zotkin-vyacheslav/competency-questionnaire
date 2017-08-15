package ru.itfbgroup.questionnaire.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itfbgroup.questionnaire.models.User;
import ru.itfbgroup.questionnaire.service.abstr.UserService;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@EnableScheduling
public class SendNotification {

	@Autowired
	private UserService userService;

	@Scheduled(cron = "0 0 19 * * ?")
	public void checkLostUsers() throws MessagingException {
		List<User> usersToSendNotification = userService.getUsersToSendNotification();

		for (User user : usersToSendNotification) {

		}
	}
}
