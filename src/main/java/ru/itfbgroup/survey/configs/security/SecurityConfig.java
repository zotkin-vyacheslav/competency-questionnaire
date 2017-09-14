package ru.itfbgroup.survey.configs.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import ru.itfbgroup.survey.configs.handlers.CustomAuthenticationSuccessHandler;
import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.Role;
import ru.itfbgroup.survey.service.abstr.RoleService;
import ru.itfbgroup.survey.service.abstr.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@PropertySource(value = {"classpath:application.properties"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
				.userDetailsContextMapper(userDetailsContextMapper())
				.userSearchBase(environment.getRequiredProperty("security.ldap.user-search-base"))
				.userSearchFilter(environment.getRequiredProperty("security.ldap.user-search-filter"))
				.contextSource()
				.url(environment.getRequiredProperty("security.ldap.url"))
				.managerDn(environment.getRequiredProperty("security.ldap.auth.manager-dn"))
				.managerPassword(environment.getRequiredProperty("security.ldap.auth.manager-password"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/admin/search").hasAnyAuthority("ROLE_MODER")
				.antMatchers("/").permitAll()
				.antMatchers("/resources/static/css/**").permitAll()
				.antMatchers("/resources/static/images/**").permitAll()
				.anyRequest().authenticated();
		http.formLogin()
				.loginPage("/login")
				.permitAll()
				.successHandler(customAuthenticationSuccessHandler)
				.and().httpBasic();
	}

	@Bean
	public UserDetailsContextMapper userDetailsContextMapper() {

		return new LdapUserDetailsMapper() {
			@Override
			public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
				ru.itfbgroup.survey.models.User user = userService.getUserByName(ctx.getStringAttribute("mail"));

				if (user == null) {
					Answer userAnswer = new Answer();
					Role role = roleService.getRoleByName("USER");
					user = new ru.itfbgroup.survey.models.User(ctx.getStringAttribute("mail"), userAnswer);
					user.setFirstName(ctx.getStringAttribute("givenname"));
					user.setLastName(ctx.getStringAttribute("sn"));
					userAnswer.setUser(user);
					Set<Role> set = new HashSet<>();
					set.add(role);
					user.setRoles(set);
					userService.addUser(user);
					logger.debug("user " + user.getEmail() + " logged first time");
					return user;
				}

				if (user.getFirstName() == null || user.getLastName() == null) {
					user.setFirstName(ctx.getStringAttribute("givenname"));
					user.setLastName(ctx.getStringAttribute("sn"));
					userService.updateUser(user);
					logger.debug("first and last name was added to " + user.getEmail());
				}

				return user;
			}
		};
	}
}
