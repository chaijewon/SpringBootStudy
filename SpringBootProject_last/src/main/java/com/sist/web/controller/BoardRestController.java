package com.sist.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {
   @PostMapping("/board/insert")
   public ResponseEntity<String> board_insert(String name,String subject,String pwd)
   {
	    System.out.println("이름:"+name);
	    System.out.println("제목:"+subject);
	    System.out.println("비번:"+pwd);
	    
	    return new ResponseEntity<>("OK",HttpStatus.OK);
   }
}
