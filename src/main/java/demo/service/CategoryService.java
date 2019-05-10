package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Category;
import demo.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findOneByIdAndGetActicles(int id) {
        return categoryRepository.findOneByIdAndGetActicles(id);
    }
}
