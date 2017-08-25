package ru.itfbgroup.questionnaire.configs.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.itfbgroup.questionnaire.models.User;
import ru.itfbgroup.questionnaire.service.abstr.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

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

	private void handle(HttpServletRequest request,
						HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	private String determineTargetUrl(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userService.getUserByName(userDetails.getUsername());
		if (user == null) {
			return "/survey";
		}
		return "/survey";
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
