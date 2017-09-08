package ru.itfbgroup.survey.configs.security;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import java.util.Collection;

public class CustomLdapUserDetails implements UserDetails {

	private LdapUserDetails details;
	private DirContextOperations context;

	public CustomLdapUserDetails(LdapUserDetails details, DirContextOperations context) {
		this.details = details;
		this.context = context;
	}

	public boolean isEnabled() {
		return details.isEnabled() && getUsername().equals(context.getStringAttribute("uid"));
	}

	public String getDn() {
		return details.getDn();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return details.getAuthorities();
	}

	public String getPassword() {
		return details.getPassword();
	}

	public String getUsername() {
		return details.getUsername();
	}

	public String getMail() {
		return context.getStringAttribute("mail");
	}

	public String getFirstName() {
		return context.getStringAttribute("givenname");
	}

	public String getLastName() {
		return context.getStringAttribute("sn");
	}

	public boolean isAccountNonExpired() {
		return details.isAccountNonExpired();
	}

	public boolean isAccountNonLocked() {
		return details.isAccountNonLocked();
	}

	public boolean isCredentialsNonExpired() {
		return details.isCredentialsNonExpired();
	}
}
