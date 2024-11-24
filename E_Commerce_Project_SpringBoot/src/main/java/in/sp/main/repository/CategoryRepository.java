package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.main.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
