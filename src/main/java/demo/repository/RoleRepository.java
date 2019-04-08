package demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import demo.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	public List<Role> findByIdIn(List<Integer> Ids);
}
