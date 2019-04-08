package demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.constant.SystemConstant;
import demo.entity.Category;
import demo.repository.CategoryRepository;
import demo.service.RenderSelectMenuService;
import demo.util.SlugUtil;

@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private RenderSelectMenuService renderSelectMenuService;
	
	@ModelAttribute
	public void commonObject(ModelMap modelMap) {
		modelMap.addAttribute("categoryLinkActive", true);
		modelMap.addAttribute("renderSelectMenuService", renderSelectMenuService);
	}

	@GetMapping("index")
	public String index(@RequestParam(name = "page", required = false) Integer page,ModelMap modelMap) {
		if (page == null) {
			page = 1;
		}
		
		int numberOfItems = (int) categoryRepository.count();
		//System.out.println(numberOfItems);
		
		int numberOfPages = (int) Math.ceil(numberOfItems * 1.0 / SystemConstant.pageSize);
		//System.out.println(numberOfPages);
		
		Sort sort = Sort.by("id").descending();

		Pageable pageable = PageRequest.of(page - 1, SystemConstant.pageSize, sort);

		List<Category> categories = categoryRepository.findPagination(pageable);
		
		modelMap.addAttribute("categories", categories);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		return "admin/category/index";
	}

	@GetMapping("add")
	public String add(Category category) {
		return "admin/category/add";
	}
	@GetMapping("view")
	public String view() {
		return "admin/category/view";
	}

	@PostMapping("add")
	public String add(@Valid @ModelAttribute("category") Category category, BindingResult errors) {
		if (errors.hasErrors()) {
			System.out.println(category);
			return "admin/category/add";
		}
		category.setSlug(SlugUtil.makeSlug(category.getName()));
		categoryRepository.save(category);
		return "redirect:/admin/category/index";
	}

	@GetMapping("edit/{id}/page/{page}")
	public String edit(@PathVariable("id") Integer id, @PathVariable("page") Integer page, ModelMap modelMap) {
		Category category = categoryRepository.findById(id).get();
		modelMap.addAttribute("category", category);
		return "admin/category/edit";
	}

	@PostMapping("edit/{id}/page/{page}")
	public String edit(@Valid @ModelAttribute("category") Category category, @PathVariable("page") Integer page, BindingResult errors) {
		if (errors.hasErrors()) {
			return "admin/category/add";
		}
		System.out.println(category);
		category.setSlug(SlugUtil.makeSlug(category.getName()));
		categoryRepository.save(category);
		return "redirect:/admin/category/index" + (page >  1 ? ("?page=" + page) : "");
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		categoryRepository.deleteById(id);
		return "redirect:/admin/category/index";
	}

}
