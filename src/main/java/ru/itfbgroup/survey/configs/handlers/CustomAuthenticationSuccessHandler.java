package ru.itfbgroup.survey.configs.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import ru.itfbgroup.survey.configs.security.CustomLdapUserDetails;
import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response, Authentication authentication) throws IOException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	public void handle(HttpServletRequest request,
					   HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication, request, response);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	private String determineTargetUrl(Authentication authentication, HttpServletRequest request,
									  HttpServletResponse response) {

		SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);

		if (savedRequest != null) {
			String srr = savedRequest.getRedirectUrl();

			if (srr.contains("update-answer")) {
				return "/update-answer";
			}
		}

//		CustomLdapUserDetails userDetails = (CustomLdapUserDetails) authentication.getPrincipal();
//		User user = userService.getUserByName(userDetails.getMail());
//
//		if (user == null) {
//			Answer userAnswer = new Answer();
//			user = new User(userDetails.getMail(), userAnswer);
//			userAnswer.setUser(user);
//			userService.addUser(user);
//			return "/answer";
//		}
		return "/answer";
	}
}
