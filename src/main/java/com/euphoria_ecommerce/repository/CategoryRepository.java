package com.euphoria_ecommerce.repository;

import com.euphoria_ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>  {

}
