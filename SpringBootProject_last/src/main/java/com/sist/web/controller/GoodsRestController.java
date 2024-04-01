package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.dao.*;
@RestController
@CrossOrigin(origins = "*")
public class GoodsRestController {
   @Autowired
   private GoodsDAO gDao;
   
   @GetMapping("/goods/list/{page}")
   public ResponseEntity<Map> goods_list(@PathVariable("page") int page)
   {
	   Map map=new HashMap();
	   try
	   {
		   int rowSize=20;
		   int start=(rowSize*page)-rowSize;
		   List<GoodsAll> list=gDao.goodsListData(start);
		   int count=(int)gDao.count();
		   map.put("gList", list);
		   map.put("count", count);
		   map.put("curpage", page);
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
   @GetMapping("/goods/find/{page}/{fd}")
   public ResponseEntity<Map> goodsFindData(@PathVariable("page") int page,
		   @PathVariable("fd") String fd)
   {
	   Map map=new HashMap();
	   try
	   {
		   int rowSize=20;
		   int start=(rowSize*page)-rowSize;
		   List<GoodsAll> list=gDao.goodsFindData(fd, start);
		   int count=gDao.goodsFindCount(fd);
		   map.put("gList", list);
		   map.put("count", count);
		   map.put("curpage", page);
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
   // 상세보기 
   @GetMapping("/goods/detail/{no}")
   public ResponseEntity<GoodsAll> goodsDetailData(
		   @PathVariable("no") int no)
   {
	    GoodsAll goods=new GoodsAll();
	    try
	    {
	    	goods=gDao.findByNo(no);
	    }catch(Exception ex)
	    {
	    	return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<>(goods,HttpStatus.OK);
   }
   
}
