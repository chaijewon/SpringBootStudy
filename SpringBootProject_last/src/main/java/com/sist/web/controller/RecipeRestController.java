package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.dao.*;
@RestController
@CrossOrigin(origins = "*")
public class RecipeRestController {
   @Autowired
   private RecipeDAO rDao;
   
   @Autowired
   private RecipedetailDAO dDao;
   
   @Autowired
   private ChefDAO cDao;
   
   @GetMapping("/recipe/list/{page}")
   public ResponseEntity<Map> recipe_list(@PathVariable("page") int page)
   {
	   Map map=new HashMap();
	   try 
	   {
		    int rowSize=20;
		    int start=(rowSize*page)-rowSize;
		    List<Recipe> rList=rDao.recipeListData(start);
		    int count=(int)rDao.count();
		    map.put("rList", rList);
		    map.put("count", count);
		    map.put("curpage", page);
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
   
   @GetMapping("/recipe/find/{page}/{title}")
   public ResponseEntity<Map> recipe_find(@PathVariable("page") int page,
		   @PathVariable("title") String title)
   {
	   Map map=new HashMap();
	   try
	   {
		   int rowSize=20;
		   int start=(rowSize*page)-rowSize;
		   List<Recipe> rList=rDao.recipeFindData(title, start);
		   int count=rDao.recipeFindCount(title);
		   map.put("rList", rList);
		   map.put("count", count);
		   map.put("curpage",page);
		   
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
   @GetMapping("/recipe/detail/{no}")
   public ResponseEntity<Map> recipe_detail(
		   @PathVariable("no") int no)
   {
	   Map map=new HashMap();
	   try 
	   {
		   Recipedetail rd=dDao.findByNo(no);
		   List<String> pList=new ArrayList<String>();
		   List<String> mList=new ArrayList<String>();
		   List<String> sList=new ArrayList<String>();
		   
		   StringTokenizer st=new StringTokenizer(rd.getStuff(),",");
		   while(st.hasMoreTokens())
		   {
			   String stuff=st.nextToken();
			   stuff=stuff.replace("구매", "");
			   sList.add(stuff);
		   }
		   
		   String[] foodmake=rd.getFoodmake().split("\n");
		   for(String fm:foodmake)
		   {
			   st=new StringTokenizer(fm,"^");
			   mList.add(st.nextToken());
			   pList.add(st.nextToken());
		   }
		   
		   map.put("rd", rd);
		   map.put("sList", sList);
		   map.put("mList", mList);
		   map.put("pList", pList);
		   
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
   
   @GetMapping("/chef/list/{page}")
   public ResponseEntity<Map> chef_list(@PathVariable("page") int page)
   {
	   Map map=new HashMap();
	   try
	   {
		   int rowSize=20;
		   int start=(rowSize*page)-rowSize;
		   List<Chef> list=cDao.chefListData(start);
		   int count=(int)cDao.count(); //long 
		   map.put("cList", list);
		   map.put("count", count);
		   map.put("curpage", page);
		   		   
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		   /*
		    *   200 => OK
		    *   404,403,500,412,415...400
		    *    |   |   |   |         |
		    *               한글 변환   전송값이 데이터형이 다른 경우 
		    *           자바 오류 발생 
		    *       보안 (접근 거부)
		    *   FileNotFound 
		    */
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
}
