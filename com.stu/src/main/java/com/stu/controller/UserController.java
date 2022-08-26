package com.stu.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stu.bean.User;
import com.stu.dao.LoginLogoutdao;
import com.stu.dao.Userdao;
import com.stu.dto.UserReq;
import com.stu.dto.UserRes;

@Controller
public class UserController {

	@Autowired
	Userdao dao;
	
	@Autowired
	LoginLogoutdao Longindao;
	
	@GetMapping("/URegister")
	public ModelAndView register(HttpServletRequest request) {
		List<UserRes> list = dao.showUser();
		request.setAttribute("user",list);
		return new ModelAndView("USR001","user",new User());		
	}
	
	@PostMapping("/UserReigster")
	public String proessingRegister(@ModelAttribute("user") User user,HttpServletRequest request) {
		
		if(user.getName().equals("") || user.getEmail().equals("") || user.getConfirm().equals("") ||
				user.getPassword().equals("") || user.getRole().equals("")) {
			request.setAttribute("error","fill all the requirement");
			return "USR001";
			
		}
	
		if(!user.getPassword().equals(user.getConfirm())) {
			request.setAttribute("error","doesnt match with password!!");
			return "USR001";
		}
		
		UserReq req= new UserReq();
		
		req.setName(user.getName());
		req.setEmail(user.getEmail());
		req.setPassword(user.getPassword());
		req.setRole(user.getRole());
		
		dao.insertUser(req);
		request.getSession().setAttribute("user",dao.showUser());
		return "redirect:/UserShow";
	}
	
	
	@GetMapping("/UserShow")
	public ModelAndView searchUser(HttpServletRequest request) {
		request.getSession().setAttribute("user",dao.showUser());
		return new ModelAndView("USR003","user", new User());
	}
	
	@PostMapping("/processingUsearch")
	public String processingSearch(@ModelAttribute("user") User user,HttpServletRequest request) {
		UserReq req = new UserReq();
		req.setId(user.getId());
		req.setName(user.getName());
		
		if(dao.specificUser(req)==null) {
			return "redirect:/UserShow?notfound='not found!!'";
		 }
		request.getSession().setAttribute("user", dao.specificUser(req));
		
		return "USR003";
	}
	
	@GetMapping("/updateUser/{id}")
	public ModelAndView updateUser(@PathVariable("id")String id,HttpServletRequest request) {

		UserReq req = new UserReq();
		req.setId(id);
		request.setAttribute("us",dao.specificUser(req));
				
		return new ModelAndView("USR002","user", new User());
	}
	
	@PostMapping("/UserUpdate")
	public String processingUpdate(@ModelAttribute("user")User user,HttpServletRequest request) {
		
		if(user.getName().equals("") || user.getEmail().equals("") || user.getConfirm().equals("") ||
				user.getPassword().equals("") || user.getRole().equals("")) {
			request.setAttribute("error","fill all the requirement");
			return "USR002";
			
		}
	
		if(!user.getPassword().equals(user.getConfirm())) {
			request.setAttribute("error","doesnt match with password!!");
			return "USR002";
		}
		
		
		UserReq req= new UserReq();
		req.setId(user.getId());
		req.setName(user.getName());
		req.setEmail(user.getEmail());
		req.setPassword(user.getPassword());
		req.setRole(user.getRole());
		
		dao.updateUser(req);
		UserRes res = (UserRes) request.getSession().getAttribute("login");
		if(req.getId().equals(res.getId())){
			request.getSession().setAttribute("login",Longindao.login(req));
		}
		
	
		request.getSession().setAttribute("user",dao.showUser());
		
		return "redirect:/UserShow";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") String id,HttpServletRequest request) {
		 UserRes res  =(UserRes) request.getSession().getAttribute("login");
		 if(res.getId().equals(id)) {
			 request.getSession().setAttribute("user",dao.showUser());
				return "redirect:/UserShow?notfoun='u cannot delete'"; 
		 }
		dao.deleteUser(id);
		request.getSession().setAttribute("user",dao.showUser());
		return "redirect:/UserShow";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
