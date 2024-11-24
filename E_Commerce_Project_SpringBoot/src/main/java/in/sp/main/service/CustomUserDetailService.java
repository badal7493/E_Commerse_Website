package in.sp.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.sp.main.model.CustomUserDetail;
import in.sp.main.model.User;
import in.sp.main.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> user=userRepository.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("User is not found"));
		
		return user.map(CustomUserDetail::new).get();
	}

}
