
package in.sp.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sp.main.global.GlobalData;
import in.sp.main.model.Role;
import in.sp.main.model.User;
import in.sp.main.repository.RoleRepository;
import in.sp.main.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	 UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	@GetMapping("/login")
	public String login() 
	{
		GlobalData.cart.clear();
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet()
	{
		return "register";
	}
	
	@PostMapping("/register")
		public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
			String password=user.getPassword();
			user.setPassword(bCryptPasswordEncoder.encode(password));
			
			 List<Role> roles = new ArrayList<>();
             roles.add(roleRepository.findById(2).get());
             user.setRoles(roles);
             userRepository.save(user);
             request.login(user.getEmail(), password);
             return "redirect:/";
	}
	

}
