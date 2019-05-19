package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AminController {
	
	@GetMapping("admincp")
	public String admincp() {
		return "admin/index/index";
	}
}
