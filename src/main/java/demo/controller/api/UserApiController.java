package demo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.constant.SystemConstant;
import demo.entity.User;
import demo.repository.UserRepository;

@RestController
@RequestMapping("api")
public class UserApiController {

	@Autowired
	private UserRepository userRepository;



	@GetMapping("user/page/{page}")//
	public List<User> findPagination(@PathVariable("page") Integer page) {
		if (page == null) {
			page = 1;
		}
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(page - 1, SystemConstant.PAGE_SIZE, sort);
		List<User> users = userRepository.findPagination(pageable);
		return users;
	}



}
