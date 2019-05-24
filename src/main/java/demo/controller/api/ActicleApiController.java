package demo.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Acticle;
import demo.service.ActicleService;

@RestController
@RequestMapping("api")
public class ActicleApiController {

	@Autowired
	private ActicleService acticleService;



	@GetMapping("acticles")
	public List<Acticle> findAll() {
		return acticleService.findAll().subList(0, 2);
	}

	@GetMapping("acticles/{id}")
	public Acticle findOneById(@PathVariable("id") int id, HttpServletResponse response) {
		Acticle acticle = acticleService.findOneById(id);
		if (acticle == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		//hong sont ranvvvvvvvvvvvvvvvvvvvvvvvvvv
		return acticle;
	}

	@RequestMapping(value = "acticles", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	public Acticle save(@RequestBody Acticle category) {
		return acticleService.save(category);
	}

}
