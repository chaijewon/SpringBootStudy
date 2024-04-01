package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 *   no int 
poster varchar(200) 
title varchar(200) 
chef varchar(200) 
chef_poster varchar(260) 
chef_profile varchar(200) 
info1 varchar(50) 
info2 varchar(50) 
info3 varchar(50) 
content text 
foodmake text 
stuff varchar(1000)
 */
@Entity
@Data
public class Recipedetail {
   @Id
   private int no;
   private String poster,title,chef,chef_poster,chef_profile,
                  info1,info2,info3,
                  content,foodmake,stuff;
}
