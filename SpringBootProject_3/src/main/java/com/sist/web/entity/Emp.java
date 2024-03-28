package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
@Entity
@Data
public class Emp {
   @Id
   private int empno;
   private int sal,deptno,mgr,comm;
   private String ename,job;
   private String hiredate;
   
	/*
	 * @PrePersist public void hiredate() {
	 * this.hiredate=LocalDateTime.now().format(DateTimeFormatter.ofPattern(
	 * "yyyy-MM-dd")); }
	 */
   
}
