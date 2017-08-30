package ru.itfbgroup.survey.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.UserService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.net.URLEncoder;
import java.util.List;

@Component
@EnableScheduling
public class NotificationSender {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserService userService;

	private final String MESSAGE = "Заполните анкету";

//	@Scheduled(cron = "0 0 11 1 * ?") //every 1 day of month 11:00
//	@Scheduled(fixedDelay = 50000)
	@Scheduled(cron = "0 12 * * 1 ?") //every 1 day of week 12:00
	public void sendNotificationToUsers() throws MessagingException {
		List<User> usersToSendNotification = userService.getUsersToSendNotification();

		for (User user : usersToSendNotification) {
			sendMail(user, MESSAGE);
			logger.debug("Message was send to " + user.getEmail());
		}
	}


	private void sendMail(User user, String message) {
		MimeMessagePreparator preparator = getMessagePreparator(user, message);
		mailSender.send(preparator);
	}

	private MimeMessagePreparator getMessagePreparator(User user, String message) {

		MimeMessagePreparator preparator = mimeMessage -> {

			mimeMessage.setRecipient(Message.RecipientType.TO,
					new InternetAddress(user.getEmail()));
			String encode = URLEncoder.encode(user.getId().toString(), "UTF-8");
			mimeMessage.setText(message + " http://localhost:8080/update/" + encode);
			mimeMessage.setSubject("Анкета по техническим компетенциям");
		};

		return preparator;
	}
}
