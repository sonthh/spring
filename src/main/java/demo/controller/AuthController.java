package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	//@Autowired
	//private BCryptPasswordEncoder encoder;
	
	@GetMapping("login")
	public String login() {
		//System.out.println(encoder.encode("123456"));
		return "auth/login";
	}
}
