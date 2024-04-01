package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.entity.*;
import com.sist.web.dao.*;
import java.util.*;
@RestController
@CrossOrigin(origins = "*")
public class MainRestController {
	@Autowired
	private EmpDAO dao;
	
	@GetMapping("/emp/list")
	public List<Emp> emp_list(Model model)
	{
		System.out.println("접속");
		List<Emp> list=dao.findAll();
		return list;
	}
}
