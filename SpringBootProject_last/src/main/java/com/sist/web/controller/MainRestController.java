package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
@RestController
@CrossOrigin(origins = "*")
public class MainRestController {
    @Autowired
    private RecipeDAO rDao;
    @Autowired
    private FoodDAO fDao;
    
    @GetMapping("/main")
    public ResponseEntity<Map> main_data(){
    	Map map=new HashMap();
    	try
    	{
    		List<Food> fList=fDao.foodMainListData();
    		Food foodOne=fDao.foodMainOneData();
    		List<Recipe> rList=rDao.recipeMainListData();
    		map.put("fList", fList);
    		map.put("rList", rList);
    		map.put("mFood", foodOne);
    	}catch(Exception ex)
    	{
    		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
