package demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.entity.Role;
import demo.entity.User;
import demo.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("check: MyUserDetailsService.loadUserByUsername()");
		
		User user = userRepository.findOneByUsernameEnable(username); //username and enable
		if (user == null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("Username not found");
		}
		
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();  
        for (Role role: user.getRoles()) {
        	SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName());
            authorities.add(authority);
		}
        
        MyUserDetails myUserDetail = new MyUserDetails(username, user.getPassword(), user.getFullName(), user.getRoles(), authorities);  
        BeanUtils.copyProperties(user, myUserDetail);
        return myUserDetail;
	}

}
