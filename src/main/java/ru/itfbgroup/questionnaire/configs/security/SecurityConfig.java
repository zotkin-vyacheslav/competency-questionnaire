package ru.itfbgroup.questionnaire.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.itfbgroup.questionnaire.configs.handlers.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
				.userSearchBase("ou=people")
				.userSearchFilter("(uid={0})")
				.groupSearchBase("ou=groups")
				.groupSearchFilter("(member={0})")
				.contextSource()
				.root("dc=example,dc=com")
				.url("ldap://hp0002:389/dc=example,dc=com");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
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
}
