package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sist.web.entity.Recipedetail;
public interface RecipedetailDAO extends JpaRepository<Recipedetail, Integer>{
    public Recipedetail findByNo(int no);
}
