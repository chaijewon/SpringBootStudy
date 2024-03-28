package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.web.entity.*;
@Repository
public interface EmpDAO extends JpaRepository<Emp, Integer>{
   
}
