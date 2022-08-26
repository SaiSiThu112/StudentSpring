package com.stu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.stu.dto.StudentReq;
import com.stu.dto.StudentRes;

import com.stu.rowmapper.StudentRowmapper;



@Repository
public class Studentdao {

	@Autowired
	JdbcTemplate jt;
	
	
	public void insertStudent(StudentReq req) {
		String sql= "insert into student (id,name,dob,gender,phone,education) values(?,?,?,?,?,?);";	
		Object [] a = {req.getId(),req.getName(),req.getDob(),req.getGender(),req.getPhone(),req.getEducation()};
		jt.update(sql,a);
		
		
		for(String z : req.getAttend()) {
		String sql1= "insert into coursedetail (studentid,courseid) values(?,?);";
		Object [] b = {req.getId(),z};
		jt.update(sql1,b);
		}	
	}
	
	public Integer studentMaxID() {
		String sql = "select max(id) as maxid from student;";
		int i = 0;
		
		try {
			i = jt.queryForObject(sql,Integer.class);
		}catch (Exception e) {
			return 0;
		}
		return i;
	}
	
	public List<StudentRes> showStudent(){
		String sql = "select group_concat(c.name) as course,s.* from student s join coursedetail cd on s.id = cd.studentid join \r\n"
				+ "course c on c.id = cd.courseid  group by s.id";
		
		List<StudentRes> list = null;
		try {
			 list = jt.query(sql,new StudentRowmapper());
	
		} catch (Exception e) {
			return null;
		}
		return list;
	}
	
	public List<StudentRes> specificStudent(StudentReq req){
		String sql = "select group_concat(c.name) as course,s.*,c.* from student s join coursedetail cd on s.id = cd.studentid join \r\n"
				+ "course c on c.id = cd.courseid  group by s.name having  s.id = ?  or  c.name = ?  or s.name = ?;";
		
 		Object [] arg = {req.getId(),req.getCourse(),req.getName()};
		List<StudentRes> list = null;
		try {
			 list = jt.query(sql,arg,new StudentRowmapper());
	
		} catch (Exception e) {
			return null;
		}
		return list;
	}
	
	public void updateStudent(StudentReq req) {
		
		String delete = "delete from coursedetail where studentid =?;";
		Object [] arg1 = {req.getId()};
		jt.update(delete, arg1);
		
		String update1 = "insert into coursedetail (studentid,courseid) values(?,?); ";
		for(String b : req.getAttend()) {
		Object[] arg2 = {req.getId(),b};
  		jt.update(update1, arg2);
		}
		
		String update = "update student set name = ? , dob = ? , gender = ? , phone = ? , education = ? where id = ?;";
		Object[] arg = {req.getName(),req.getDob(),req.getGender(),req.getPhone(),req.getEducation(),req.getId()};
		jt.update(update,arg);
	}
	
	
	public void deleteStudent(StudentReq req) {
		
		String delete = "delete from coursedetail where studentid =?;";
		Object [] arg1 = {req.getId()};
		jt.update(delete, arg1);
		
		
		String delete2 = "delete from student where id =?;";
		Object [] arg2 = {req.getId()};
		jt.update(delete2, arg2);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
