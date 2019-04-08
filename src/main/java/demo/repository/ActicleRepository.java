package demo.repository;

import org.springframework.data.repository.CrudRepository;

import demo.entity.Acticle;

public interface ActicleRepository extends CrudRepository<Acticle, Integer> {

}
