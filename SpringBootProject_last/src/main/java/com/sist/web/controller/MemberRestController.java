package com.sist.web.controller;
import com.sist.web.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import java.util.*;
@RestController
@CrossOrigin(origins = "*")
public class MemberRestController {
   @Autowired
   private MemberDAO mDao;
  
   @GetMapping("/member/login/{id}/{pwd}")
   public ResponseEntity<Map> memberLogin(@PathVariable("id") String id,
		   @PathVariable("pwd") String pwd)
   {
	   Map map=new HashMap();
	   try
	   {
		   // map => id , name , msg => onSuccess / onError
		   int count=mDao.idCount(id);
		   if(count==0)
		   {
			   map.put("msg", "NOID");
		   }
		   else
		   {
			   Member mem=mDao.findById(id);
			   if(pwd.equals(mem.getPwd()))
			   {
				   map.put("name", mem.getName());
				   map.put("id", mem.getId());
				   map.put("msg", "OK");
			   }
			   else
			   {
				  map.put("msg", "NOPWD"); 
			   }
		   }
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
}
