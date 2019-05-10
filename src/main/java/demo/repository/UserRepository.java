package demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import demo.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("select u from User u")
	List<User> findPagination(Pageable pageable);

	@Query("select u from User u where u.username = :username")
	User findOneByUsernameEnable(@Param("username") String username);
}
