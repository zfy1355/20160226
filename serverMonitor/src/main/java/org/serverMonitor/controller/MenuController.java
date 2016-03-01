package org.serverMonitor.controller;

import org.serverMonitor.entity.Menu;
import org.serverMonitor.json.RespDataCode;
import org.serverMonitor.json.RespJSON;
import org.serverMonitor.json.RespListJSON;
import org.serverMonitor.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	public MenuController() {
		System.out.println("MENU Controller");
	}
	
	@RequestMapping(value="/menulist")
	public ModelAndView listMenu(Model model){
		Iterable<Menu> menus =  menuService.findAll();
		model.addAttribute("menus", menus);
		return new ModelAndView("menu/listMenu");
	}
	
	@RequestMapping(value="/getMenus", produces = "text/json;charset=UTF-8")
	@ResponseBody 
	public RespJSON<Menu> getMenu(){
		return new RespJSON<Menu>(RespDataCode.NO_CITY_DATA);
	/*	Iterable<Menu> menus = menuService.findAll();
		Iterator<Menu>menuIterator = menus.iterator();
		List<Menu> menuList = new ArrayList<Menu>();
		while(menuIterator.hasNext()){
			menuList.add(menuIterator.next());
		}*/
		
		/*if (ObjectUtil.isNotEmpty(menuList)) {
			return new RespListJSON<Menu>(menuList);
		} else {
			return new RespListJSON<Menu>(RespDataCode.NO_CITY_DATA);
		}*/
		
	}
}
