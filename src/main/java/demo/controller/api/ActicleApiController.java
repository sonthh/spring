package demo.controller.api;

import demo.entity.Acticle;
import demo.service.ActicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ActicleApiController {

	@Autowired
	private ActicleService acticleService;


	@GetMapping("acticles")
	public List<Acticle> findAll() {
		return acticleService.findAll();
	}

}
