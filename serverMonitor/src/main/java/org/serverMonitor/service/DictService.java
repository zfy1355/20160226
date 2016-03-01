package org.serverMonitor.service;

import java.util.ArrayList;
import java.util.List;

import org.serverMonitor.dao.DictDao;
import org.serverMonitor.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

public class DictService {
	@Autowired
	private DictDao dao;
	
	@Cacheable(value="dict", key="'name'")
	public List<Dict> getcities(){
		List<Dict> dictes = new ArrayList<Dict>();
		return dictes;
	}

}
