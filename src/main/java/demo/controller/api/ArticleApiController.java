package demo.controller.api;

import demo.constant.SystemConstant;
import demo.entity.Acticle;
import demo.entity.Category;
import demo.entity.Foo;
import demo.repository.ActicleRepository;
import demo.repository.CategoryRepository;
import demo.service.ActicleService;
import demo.service.RenderSelectMenuService;
import demo.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class ArticleApiController {

	@Autowired
	private ActicleService acticleService;

	@GetMapping("acticles")
	public List<Acticle> findAll() {
		return acticleService.findAll();
	}

}
