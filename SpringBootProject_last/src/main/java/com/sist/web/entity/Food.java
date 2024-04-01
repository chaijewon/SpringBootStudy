package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 *   FNO int PK 
POSTER text 
NAME text 
TYPE text 
ADDRESS text 
PHONE text 
SCORE double 
THEME text 
PRICE text 
TIME text 
SEAT text 
CONTENT text 
LINK text 
HIT int 
JJIMCOUNT int 
LIKECOUNT int
 */
@Entity(name="food_house")
@Data
public class Food {
   @Id
   private int fno;
   private String poster,name,address,phone,theme,price,time,
           seat,content,link;
   private int hit,jjimcount,likecount;
   private double score;
}
