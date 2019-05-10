package demo.service;

import demo.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import demo.entity.Category;
import demo.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findOneByIdAndGetActicles(int id) {
        return categoryRepository.findOneByIdAndGetActicles(id);
    }

    public List<Category> findAllPagination(int page, Sort sort) {
        Pageable pageable = PageRequest.of(page - 1, SystemConstant.PAGE_SIZE, sort);
        List<Category> categories = categoryRepository.findPagination(pageable);
        return categories;
    }
}
