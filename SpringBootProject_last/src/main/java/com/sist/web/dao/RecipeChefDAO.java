package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.RecipeEntity;
import java.util.*;
public interface RecipeChefDAO extends JpaRepository<RecipeEntity, Integer>{
    @Query(value="SELECT * FROM recipe "
    		    +"WHERE chef=:chef "
    		    +"ORDER BY no ASC "
    		    +"LIMIT :start,20",nativeQuery = true)
    public List<RecipeEntity> chefRecipeData(@Param("chef") String chef,
    		    @Param("start") int start);
    @Query(value="SELECT COUNT(*) FROM recipe "
    		    +"WHERE chef=:chef",nativeQuery = true)
    public int chefRecipeCount(@Param("chef") String chef);
}
