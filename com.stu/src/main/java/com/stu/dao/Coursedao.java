package com.stu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stu.dto.CourseReq;
import com.stu.dto.CourseRes;
import com.stu.rowmapper.CourseRowmapper;
		


@Repository
public class Coursedao {

		@Autowired
		JdbcTemplate jt;
	
	
		public void insertCourse(CourseReq req) {
			String sql = "insert into course (id,name) values(?,?);";
			Object [] a = {req.getId(),req.getName()};
			jt.update(sql, a);	
		}
	
		
		
		public Integer coureMaxid() {
			String sql = "select max(id) as maxid from course;";
			int i = 0;
			
			try {
				i = jt.queryForObject(sql,Integer.class);
			}catch (Exception e) {
				return 0;
			}
			return i;
		}
	
		
	public List<CourseRes> showCourse() {
			String sql = "select * from course;";
			List<CourseRes> list = null;
			try {
				 list = jt.query(sql,new CourseRowmapper());
		
			} catch (Exception e) {
				return null;
			}
			return list;	
	}
	
	
	
	
	
	
	
	
	
	
	
}
