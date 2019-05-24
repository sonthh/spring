package demo.controller.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Category;
import demo.service.CategoryService;

@RestController
@RequestMapping("api")
public class CategoryApiController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("categories/{id}")//
	public Category findOneByIdAndGetActicles(@PathVariable("id") int id, HttpServletResponse response) {
		Category category = categoryService.findOneByIdAndGetActicles(id);
		if (category == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		return category;
	}

}
