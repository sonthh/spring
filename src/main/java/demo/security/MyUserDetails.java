package demo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import demo.entity.Role;

public class MyUserDetails implements UserDetails { //hoặc extends org.springframework.security.core.userdetails.User;
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String fullname;
	private List<Role> roles;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(String userName, String password, String fullname, List<Role> roles, List<GrantedAuthority> authorities) {
		this.userName = userName;
		this.password = password;
		this.fullname = fullname;
		this.roles = roles;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getFullname() {
		return fullname;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	
}
