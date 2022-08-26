package com.stu.dao;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import com.stu.dto.UserReq;
import com.stu.dto.UserRes;
import com.stu.rowmapper.UserRowmapper;
 

@Repository
public class LoginLogoutdao {

	
	@Autowired
	JdbcTemplate jt;
	
	
	public UserRes login(UserReq req) {
		String sql = "select * from user where id = ? and password = ?;";
		Object[] arg = {req.getId(),req.getPassword()};
		UserRes res = null;
	
		try {
			 res= jt.queryForObject(sql, arg, new UserRowmapper());
		}catch (Exception e) {
			return null;
		}
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
