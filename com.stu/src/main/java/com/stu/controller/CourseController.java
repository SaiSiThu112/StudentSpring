package com.stu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stu.bean.Course;
import com.stu.dao.Coursedao;
import com.stu.dto.CourseReq;

@Controller
public class CourseController {

	@Autowired
	Coursedao dao;
	
	@GetMapping("/courseRegister")
	public ModelAndView courseRegister() {
		return new ModelAndView("BUD003","course",new Course());
	}
	
	@PostMapping("/CourseRegister")
	public String processingResgister(@ModelAttribute("course") Course course,HttpServletRequest request) {
		
		if(request.getParameter("id").equals("") || request.getParameter("name").equals("")) {
			request.setAttribute("error","fill all information");
			return "BUD003";
			}
		CourseReq req = new CourseReq();
		req.setId(course.getId());
		req.setName(course.getName());
		
		dao.insertCourse(req);
		int i = dao.coureMaxid();
		i += 1;
		request.getSession().setAttribute("cid",i);
		request.getSession().setAttribute("course",dao.showCourse());
		return "redirect:/courseRegister";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
