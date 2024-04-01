package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import com.sist.web.entity.*;
public interface FoodDAO extends JpaRepository<Food, Integer>{
    @Query(value = "SELECT * FROM food_house "
    		     + "ORDER BY fno ASC "
    		     + "LIMIT 0,8",nativeQuery = true)
    public List<Food> foodMainListData();
    
    @Query(value = "SELECT * FROM food_house "
		     + "ORDER BY fno ASC "
		     + "LIMIT 0,1",nativeQuery = true)
    public Food foodMainOneData();
    
    @Query(value = "SELECT * FROM food_house "
    		      +"ORDER BY fno ASC "
    		      +"LIMIT :start,20",nativeQuery = true)
    // 페이지 목록 
    public List<Food> foodListData(@Param("start") int start);
    // 상세보기 
    public Food findByFno(int fno);
    
    @Query(value="SELECT * FROM food_house "
    		    +"WHERE address LIKE CONCAT('%',:address,'%') "
    		    +"ORDER BY fno ASC "
    		    +"LIMIT :start,20",nativeQuery = true)
    public List<Food> foodFindData(@Param("address") String address,
    		@Param("start") Integer start);
    
    @Query(value = "SELECT COUNT(*) FROM food_house "
    		      +"WHERE address LIKE CONCAT('%',:address,'%')",
    		      nativeQuery = true)
    public int foodFindCount(@Param("address") String address);
    
}
