package demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Không được nhập rỗng")
	private String name;
	
	@NotNull(message = "Không được nhập rỗng")
	/*@Min(1)
	@Max(10)*/
	private Integer parentId;
	
	@NotNull(message = "Không được nhập rỗng")
	/*@Min(1)
	@Max(10)*/
	private Integer sort;
	
	private String slug;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
//	@JsonIgnore
	private List<Acticle> acticles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public List<Acticle> getActicles() {
		return acticles;
	}

	public void setActicles(List<Acticle> acticles) {
		this.acticles = acticles;
	}

	public Category(Integer id, String name, Integer parentId, Integer sort, String slug, List<Acticle> acticles) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.sort = sort;
		this.slug = slug;
		this.acticles = acticles;
	}

	public Category(Integer id, String name, Integer parentId, Integer sort, String slug) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.sort = sort;
		this.slug = slug;
	}

	public Category() {
		super();
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", parentId=" + parentId + ", sort=" + sort + ", slug=" + slug
				+ "]";
	}

}
