package org.serverMonitor.service;

import org.serverMonitor.dao.MenuDao;
import org.serverMonitor.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
	@Autowired
	private MenuDao menuDao;
	public MenuService(){
		System.out.println("MENU SERVICE");
	}
	
	public Iterable<Menu> findAll(){
		return menuDao.findAll();
	}

}
