package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

	@GetMapping("")
	public String test(ModelMap modelMap) {
		modelMap.addAttribute("fullName", "Trần Hữu Hồng Sơn");
		return "test/test";
	}
	
	@GetMapping("ajax")
	public String ajax(ModelMap modelMap) {
		return "test/ajax";
	}
	
}
