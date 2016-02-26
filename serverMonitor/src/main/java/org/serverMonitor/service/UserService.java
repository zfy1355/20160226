package org.serverMonitor.service;


import org.serverMonitor.dao.UserDao;
import org.serverMonitor.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userdao;
	
	public User getUser(String username){
		return userdao.findByUsername(username);
	}
}
