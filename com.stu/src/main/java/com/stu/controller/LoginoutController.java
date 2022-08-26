package com.stu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.stu.bean.User;
import com.stu.dao.Coursedao;
import com.stu.dao.LoginLogoutdao;
import com.stu.dao.Studentdao;
import com.stu.dao.Userdao;
import com.stu.dto.UserReq;

@Controller
public class LoginoutController {
	
	@Autowired
	LoginLogoutdao Longindao;
	
	@Autowired
	Userdao userdao;
	
	@Autowired
	Coursedao couresdao;
	
	@Autowired
	Studentdao studentdao;
	
	@GetMapping("/")
	public ModelAndView welcome() {
		return new ModelAndView("LGN001","user",new User());	
	}
	
	@PostMapping("/Login")
	public String login(@ModelAttribute("user") User user,HttpServletRequest request) {
		UserReq req = new UserReq();
		req.setId(user.getId());
		req.setPassword(user.getPassword());
		
		if(Longindao.login(req) == null) {
			return "redirect:/?error='not found!!'";
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now(); 
				
	
		   if(couresdao.coureMaxid()!=0) {
				int i = couresdao.coureMaxid();
				i+= 1;
				System.out.println(i);
				request.getSession().setAttribute("cid",i);
			} 
		   
		   if(studentdao.studentMaxID()!=0) {
				int i = studentdao.studentMaxID();
				i+= 1;
				
				request.getSession().setAttribute("stuno",i);
			}
		   
		   
	request.getSession().setAttribute("student",studentdao.showStudent());	   
	request.getSession().setAttribute("course",couresdao.showCourse());	   
	request.getSession().setAttribute("user",userdao.showUser());
	request.getSession().setAttribute("login",Longindao.login(req));
	request.getSession().setAttribute("date",dtf.format(now));
		
		return "MNU001";
	}
	
	
	@GetMapping("/Logout")
	public String logout(HttpServletRequest request) {
		request.getSession(false);
		request.getSession().invalidate();
		
		return "redirect:/";
	}
	
	
	
	
	
}
