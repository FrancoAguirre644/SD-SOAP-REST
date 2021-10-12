package com.ecommerce.ecommerce.implementation;

import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String usernameAux = userRepository.userNameByEmailInUsername(username);
		com.ecommerce.ecommerce.entities.User user = null;
		
		if(usernameAux != null) {
			user = userRepository.findByUsernameAndFetchUserRolesEagerly(usernameAux);
		}else {
			user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		}
		
		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
	}
	
	private User buildUser(com.ecommerce.ecommerce.entities.User user, List<GrantedAuthority> grantedAuthorities) {

		
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for(UserRole userRole: userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
}