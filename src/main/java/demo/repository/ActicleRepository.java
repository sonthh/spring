package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import demo.entity.Acticle;

public interface ActicleRepository extends CrudRepository<Acticle, Integer> {
	
}
