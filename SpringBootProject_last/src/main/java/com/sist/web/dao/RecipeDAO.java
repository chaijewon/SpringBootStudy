package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Recipe;
import java.util.*;
public interface RecipeDAO extends JpaRepository<Recipe, Integer>{
   @Query(value = "SELECT * FROM recipe2 "
		         +"ORDER BY no ASC "
		         +"LIMIT 0,5",nativeQuery = true)
   public List<Recipe> recipeMainListData();
   /*
    *   SELECT r.* FROM Recipe r => JPQL
    */
   @Query(value = "SELECT * FROM recipe2 "
		         +"ORDER BY no ASC "
		         +"LIMIT :start,20",nativeQuery = true)
   public List<Recipe> recipeListData(@Param("start") int start);
   // 상세보기 
   public Recipe findByNo(int no);
   
   //public List<Recipe> findByTitleContaining(String title);
   // JOIN , SubQuery => JPA + MyBatis 
   // 레시피 찾기 
   @Query(value="SELECT * FROM recipe2 "
		       +"WHERE title LIKE CONCAT('%',:title,'%') "
		       +"ORDER BY no ASC "
		       +"LIMIT :start,20",nativeQuery = true)
   public List<Recipe> recipeFindData(@Param("title") String title,
		   @Param("start") int start);
   @Query(value="SELECT COUNT(*) FROM recipe2 "
		       +"WHERE title LIKE CONCAT('%',:title,'%')",
		       nativeQuery = true)
   public int recipeFindCount(@Param("title") String title);
   
}
