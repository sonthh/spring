package demo.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	//@Autowired
	//private BCryptPasswordEncoder encoder;
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("login")
	public String login() {
//		System.out.println(servletContext.getRealPath(""));
		//System.out.println(encoder.encode("123456"));
		return "auth/login";
	}
}
