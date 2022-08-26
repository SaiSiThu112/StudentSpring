package com.stu.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stu.dto.UserReq;
import com.stu.dto.UserRes;
import com.stu.rowmapper.UserRowmapper;

@Repository
public class Userdao {

	@Autowired
	JdbcTemplate jt;
	
	
	public void insertUser(UserReq req) {
		String sql= "insert into user (name,email,password,role) values(?,?,?,?);";
		Object[] arg = {req.getName(),req.getEmail(),req.getPassword(),req.getRole()};
		jt.update(sql,arg);
	}
	
	public void updateUser(UserReq req) {
		String sql = "update user set name=? , email = ? , password = ?, role = ? where id = ?;"; 
		Object[] arg = {req.getName(),req.getEmail(),req.getPassword(),req.getRole(),Integer.valueOf(req.getId())};
		jt.update(sql, arg);
	
	}
	
	public void deleteUser(String i) {
		String sql = " delete from user where id= ?;";
		jt.update(sql,i);
	}
	
	
	public List<UserRes> showUser() {
		String sql = "select * from user;";

	   List<UserRes> list = jt.query(sql,new UserRowmapper());
	   
	   return list;	
	}
	
	
	
	public List<UserRes> specificUser(UserReq req) {
		String sql = "select * from user where id = ? or name = ?;";
		Object [] arg = {req.getId(),req.getName()};
		 List<UserRes> list = new ArrayList<>();
	 
		try {
			UserRes res = jt.queryForObject(sql, arg, new UserRowmapper());
			list.add(res);
			
		} catch (Exception e) {
			return null;
		}
	   return list;	
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
