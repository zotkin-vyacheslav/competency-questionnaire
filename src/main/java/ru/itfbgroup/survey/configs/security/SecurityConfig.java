package ru.itfbgroup.survey.configs.security;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import ru.itfbgroup.survey.configs.handlers.CustomAuthenticationSuccessHandler;

import java.util.Collection;

@Configuration
@EnableWebSecurity
@PropertySource(value = {"classpath:application.properties"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

//	@Autowired
//	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication()
//				.userDetailsContextMapper(userDetailsContextMapper())
//				.userSearchBase("ou=people")
//				.userSearchFilter("(uid={0})")
//				.groupSearchBase("ou=groups")
//				.groupSearchFilter("(member={0})")
//				.contextSource()
//				.root("dc=example,dc=com")
//				.url("ldap://hp0002:389/dc=example,dc=com");
//	}

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
				.antMatchers("/").permitAll()
				.antMatchers("/resources/static/css/**").permitAll()
				.antMatchers("/resources/static/images/**").permitAll()
				.anyRequest().authenticated();
		http.formLogin()
				.loginPage("/login")
				.permitAll()
				.successHandler(customAuthenticationSuccessHandler)
				.and().httpBasic();
//				.and()
//				.logout().logoutSuccessUrl("/login?logout");
	}

	@Bean
	public UserDetailsContextMapper userDetailsContextMapper() {
		return new LdapUserDetailsMapper() {
			@Override
			public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
				UserDetails details = super.mapUserFromContext(ctx, username, authorities);
				return new CustomLdapUserDetails((LdapUserDetails) details, ctx);
			}
		};
	}
}
