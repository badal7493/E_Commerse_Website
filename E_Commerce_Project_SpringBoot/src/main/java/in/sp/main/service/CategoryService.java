package in.sp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.model.Category;
import in.sp.main.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	//for adding category
	public void addCategory(Category category)
	{
		categoryRepository.save(category);
	}
	
	//for getting all category
	
	public List<Category> getAllCategory()
	{
		return categoryRepository.findAll();
	}
	
	//delete Category
	
	public void removeCategoryById(int id)
	{
		categoryRepository.deleteById(id);
	}
	
	//update/fetch category
	
	public Optional<Category> getCategoryById(int id)
	{
		return categoryRepository.findById(id);
	}

}
