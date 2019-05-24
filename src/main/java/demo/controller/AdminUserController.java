package demo.controller;

import java.util.ArrayList;
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
import demo.entity.Role;
import demo.entity.User;
import demo.repository.RoleRepository;
import demo.repository.UserRepository;

@Controller
@RequestMapping("admin/user")
public class AdminUserController {//

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@ModelAttribute
	public void commonObject(ModelMap modelMap) {
		modelMap.addAttribute("userLinkActive", true);
		
		List<Role> roles = new ArrayList<Role>();
		roleRepository.findAll().forEach(r -> roles.add(r));
		modelMap.addAttribute("roles", roles);
	}

	@GetMapping("index")
	public String index(@RequestParam(name = "page", required = false) Integer page,ModelMap modelMap) {
		if (page == null) {
			page = 1;
		}
		
		int numberOfItems = (int) userRepository.count();
		
		int numberOfPages = (int) Math.ceil(numberOfItems * 1.0 / SystemConstant.PAGE_SIZE);
		
		Sort sort = Sort.by("id").descending();

		Pageable pageable = PageRequest.of(page - 1, SystemConstant.PAGE_SIZE, sort);

		List<User> users = userRepository.findPagination(pageable);
		modelMap.addAttribute("users", users);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		//System.out.println("AdminUserController.index()");
		return "admin/user/index";
	}
	
	@GetMapping("add")
	public String add(User user, ModelMap modelMap) {
		return "admin/user/add";
	}
	
	@PostMapping("add")
	public String add(@Valid @ModelAttribute("user") User user, BindingResult errors, 
			@RequestParam(name = "roleIds", required = false) List<Integer> roleIds) {
		if (roleIds == null) {
			errors.rejectValue("roles", "user", "Vui lòng chọn quyền");
		}
		
		if (user.getPassword().trim().equals("")) {
			errors.rejectValue("password", "user", "Password không được rỗng");
		}
		if (errors.hasErrors()) {
			return "admin/user/add";
		}
		
		List<Role> roles = roleRepository.findByIdIn(roleIds);
		user.setRoles(roles);
		
		userRepository.save(user);
		
		return "redirect:/admin/user/index";
	}
	
	@GetMapping("edit/{id}/page/{page}")
	public String edit(@PathVariable("id") Integer id, @PathVariable("page") Integer page, ModelMap modelMap) {
		User user= userRepository.findById(id).get();
		List<Integer> oldRoleIds = new ArrayList<Integer>();
		user.getRoles().forEach(r -> oldRoleIds.add(r.getId()));
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("oldRoleIds", oldRoleIds);
		return "admin/user/edit";
	}
	
	@PostMapping("edit/{id}/page/{page}")
	public String edit(@Valid @ModelAttribute("user") User user, BindingResult errors, 
			@PathVariable("page") Integer page,
			@RequestParam(name = "roleIds", required = false) List<Integer> roleIds, ModelMap modelMap) {
		if (roleIds == null) {
			errors.rejectValue("roles", "user", "Vui lòng chọn quyền");
		}
		
		if (user.getPassword().trim().equals("")) {
			errors.rejectValue("password", "user", "Password không được rỗng");
		}
		if (errors.hasErrors()) {
			List<Integer> oldRoleIds = new ArrayList<Integer>();
			//User oldUser = userRepository.findById(user.getId()).get();
			//oldUser.getRoles().forEach(r -> oldRoleIds.add(r.getId()));
			modelMap.addAttribute("oldRoleIds", oldRoleIds);
			return "admin/user/edit";
		}
		
		List<Role> roles = roleRepository.findByIdIn(roleIds);
		user.setRoles(roles);
		
		userRepository.save(user);
		
		return "redirect:/admin/user/index" + (page >  1 ? ("?page=" + page) : "");
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		userRepository.deleteById(id);
		return "redirect:/admin/user/index";
	}

}
