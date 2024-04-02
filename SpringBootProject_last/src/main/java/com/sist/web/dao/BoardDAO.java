package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;
public interface BoardDAO extends JpaRepository<Board, Integer>{
   // 목록 
   @Query(value = "SELECT * FROM jpaboard "
		        + "ORDER BY no DESC "
		        + "LIMIT :start,10",nativeQuery = true)
   public List<Board> boardListData(@Param("start") int start);
   // 총페이지 => count()
   // Update => save
   // Delete => delete
   //public Board deleteByNo(int no);// where no=1
   // Insert => save
   // 상세보기
   public Board findByNo(int no);
   
   
}
