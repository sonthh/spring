package demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Category;
import demo.repository.CategoryRepository;

@Service
public class RenderSelectMenuService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private HttpServletRequest request;

	public String printChildMenu(int parentId) {
		List<Category> categories = categoryRepository.findByParentId(parentId);
		StringBuilder result = new StringBuilder();
		if (categories.size() > 0) {
			result.append("<ul>");
			for (Category category : categories) {
				String href = request.getContextPath() + "/admin/category/edit/" + category.getId();
				result.append("<li><a href='" + href +"'>" + category.getName() + "</a>");
				result.append(this.printChildMenu(category.getId()));
			}
			result.append("</ul>");
		} else {
			result.append("</li>");
		}
		return result.toString();
	}

	public String printAllMenu() {
		List<Category> categories = categoryRepository.findByParentId(0);
		StringBuilder result = new StringBuilder();
		result.append("<ul>");
		for (Category category : categories) {
			String href = request.getContextPath() + "/admin/category/edit/" + category.getId();
			result.append("<li><a href='" + href +"'>" + category.getName() + "</a>");
			result.append(this.printChildMenu(category.getId()));
		}
		result.append("</ul>");
		return result.toString();
	}
	
	public String printOptionSelect(int parentId, int level) {
		List<Category> categories = categoryRepository.findByParentId(parentId);
		StringBuilder result = new StringBuilder();
		if (categories.size() > 0) {
			for (Category category : categories) {
				result.append("<option value='"+category.getId()+"' class='option-level-" + level + "'>" + this.printSpace(level) + category.getName() + "</option>");
				result.append(this.printOptionSelect(category.getId(), level + 1));
			}
		}
		return result.toString();
	}
	
	public String printAllSelect() {
		List<Category> categories = categoryRepository.findByParentId(0);
		StringBuilder result = new StringBuilder();
		result.append("<option class='option-level-0' value='0'>None</option>");
		for (Category category : categories) {
			result.append("<option value='"+category.getId()+"' class='option-level-0'>" + this.printSpace(0) +  category.getName() + "</option>");
			result.append(this.printOptionSelect(category.getId(), 1));
		}
		return result.toString();
	}
	public String printAllSelect(int id) {
		List<Category> categories = categoryRepository.findByParentId(0);
		StringBuilder result = new StringBuilder();
		result.append("<option class='option-level-0' value='0'>None</option>");
		for (Category category : categories) {
			String selected = "";
			if (category.getId() == id)
				selected = "selected";
			result.append("<option "+ selected +" value='"+category.getId()+"' class='option-level-0'>" + this.printSpace(0) +  category.getName() + "</option>");
			result.append(this.printOptionSelect(category.getId(), 1, id));
		}
		return result.toString();
	}
	
	public String printOptionSelect(int parentId, int level, int id) {
		List<Category> categories = categoryRepository.findByParentId(parentId);
		StringBuilder result = new StringBuilder();
		if (categories.size() > 0) {
			for (Category category : categories) {
				String selected = "";
				if (category.getId() == id)
					selected = "selected";
				result.append("<option "+selected+" value='"+category.getId()+"' class='option-level-" + level + "'>" + this.printSpace(level) + category.getName() + "</option>");
				result.append(this.printOptionSelect(category.getId(), level + 1, id));
			}
		}
		return result.toString();
	}
	
	public String printSpace(int level) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < level; i++) {
			result.append("&nbsp;&nbsp;&nbsp;");
		}
		return result.toString();
	}

}
