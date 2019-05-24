package demo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Acticle;
import demo.service.ActicleService;

@RestController
@RequestMapping("rest")
public class ActicleApiController2 {
	
	@Autowired
	private ActicleService acticleService;
	
	//hongssontra sadfasf

	@RequestMapping(path = "acticles", method = RequestMethod.GET)
	public ResponseEntity<List<Acticle>> findAll() {
		List<Acticle> acticles = acticleService.findAll();
		return new ResponseEntity<List<Acticle>>(acticles, HttpStatus.OK);
	}
	
}
