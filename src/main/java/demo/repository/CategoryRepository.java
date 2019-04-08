package demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	//JPQL QUERY
	@Query("select c from Category c where c.parentId = :parentId")
	public List<Category> demo1(@Param("parentId") Integer parentId);
	
	@Query("select c from Category c")
	public List<Category> findPagination(Pageable pageable);
	
	
	//NATIVE QUERY
	@Query(value = "select * from categories where parentId = :parentId", nativeQuery = true)
	public List<Category> demo2(@Param("parentId") Integer parentId);
	
	//QUERY METHODS: CHỈ CẦN ĐẶT TÊN THEO QUY TẮC THÌ MỌI THỨ ĐỂ JPA LO
	public List<Category> findByParentId(Integer parentId);
	
	public Category findOneById(Integer id);
	
	@Query("from Category c JOIN FETCH c.acticles WHERE c.id = :id")
	public Category findOneByIdAndGetActicles(@Param("id") Integer id);
	
}
