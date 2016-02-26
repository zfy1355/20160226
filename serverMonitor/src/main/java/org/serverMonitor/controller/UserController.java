package org.serverMonitor.controller;

import java.util.ArrayList;

import org.serverMonitor.entity.User;
import org.serverMonitor.json.RespListJSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@RequestMapping(value="addUser")
	public RespListJSON<User> addUser(){
		return new RespListJSON<User>(new ArrayList<User>());
	}
	
	@RequestMapping(value="deleteUser")
	public ModelAndView deleteUser(){
		return new ModelAndView();
	}
	
	@RequestMapping(value="listUser")
	public ModelAndView listUser(){
		return new ModelAndView();
	}
	
}
