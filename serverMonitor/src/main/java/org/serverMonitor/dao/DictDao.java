package org.serverMonitor.dao;

import org.serverMonitor.entity.Dict;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictDao extends CrudRepository<Dict, String>{

	public Dict findByKey(String username);
	
}
