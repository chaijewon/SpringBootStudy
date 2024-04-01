package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import com.sist.web.entity.*;
public interface ChefDAO extends JpaRepository<Chef, Integer>{
   @Query(value="SELECT * FROM chef "
		       +"ORDER BY cno ASC "
		       +"LIMIT :start,20",nativeQuery = true)
   public List<Chef> chefListData(@Param("start") int start);
   // count =>  검색 => SQL
}
