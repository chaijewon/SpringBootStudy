package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import com.sist.web.entity.*;
@RestController
@CrossOrigin(origins = "*")
public class BoardRestController {
  // DAO객체 => Spring에서 관리 
  @Autowired
  private BoardDAO bDao;
  // 목록 @GetMapping => axios.get
  @GetMapping("/board/list/{page}")
  public ResponseEntity<Map> boardListData(@PathVariable("page") int page)
  {
	  Map map=new HashMap();
	  try
	  {
		  int rowSize=10;
		  int start=(rowSize*page)-rowSize; // LIMIT => 0번부터 시작 
		  List<Board> list=bDao.boardListData(start);
		  for(Board bb:list)
		  {
			  String day=bb.getRegdate();
			  day=day.substring(0,day.indexOf(" "));
			  bb.setRegdate(day.trim());
		  }
		  int totalpage=(int)(Math.ceil(bDao.count()/10.0));
		  map.put("bList", list);
		  map.put("totalpage", totalpage);
		  map.put("curpage", page);
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(map,HttpStatus.OK);
  }
  // 상세 @GetMapping => axios.get
  @GetMapping("/board/detail/{no}")
  public ResponseEntity<Board> boardDetailData(@PathVariable("no") int no)
  {
	    Board board=null;
	    try
	    {
	    	board=bDao.findByNo(no);
	    	board.setHit(board.getHit()+1);
	    	bDao.save(board);
	    	board=bDao.findByNo(no);
	    }catch(Exception ex)
	    {
	    	return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<>(board,HttpStatus.OK);
  }
  // 추가 @PostMapping => axios.post
  @PostMapping("/board/insert")
  public ResponseEntity<Map> boardInsert(@RequestBody Board board)
  {
	  Map map=new HashMap();
	  try
	  {
		  Board _board=bDao.save(board);
		  map.put("board", _board);
		  map.put("msg", "yes");
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(map,HttpStatus.CREATED);
  }
  // 수정 @PutMapping  => axios.put
  @GetMapping("/board/update/{no}")
  public ResponseEntity<Board> boardUpdateData(@PathVariable("no") int no)
  {
	  Board board=null;
	  try
	  {
		  board=bDao.findByNo(no);
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(board,HttpStatus.OK);
  }
  
  @PutMapping("/board/update_ok/{no}")
  public ResponseEntity<Map> boardUpDataOk(@PathVariable("no") int no,
		  @RequestBody Board board)
  {
	  Map map=new HashMap();
	  try
	  {
		  Board dbBoard=bDao.findByNo(no);
		  if(dbBoard.getPwd().equals(board.getPwd()))
		  {
			  board.setNo(no);
			  board.setHit(dbBoard.getHit());
			  Board b=bDao.save(board);
			  map.put("board", b);
			  map.put("msg", "yes");
		  }
		  else
		  {
			  map.put("msg", "no");
		  }
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	  return new ResponseEntity<>(map,HttpStatus.OK);
  }
  // 삭제 @DeleteMapping => axios.delete
  @DeleteMapping("/board/delete/{no}/{pwd}")
  public ResponseEntity<Map> boardDelete(@PathVariable("no") int no,
		  @PathVariable("pwd") String pwd)
  {
	  Map map=new HashMap();
	  try
	  {
		  Board board=bDao.findByNo(no);
		  if(pwd.equals(board.getPwd()))
		  {
			  bDao.delete(board);
			  map.put("msg", "yes");
		  }
		  else
		  {
			  map.put("msg", "no");
		  }
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(map,HttpStatus.OK);
  }
  // => @PathValiable /{}
}
