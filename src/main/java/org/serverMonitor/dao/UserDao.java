package org.serverMonitor.dao;

import org.serverMonitor.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDao extends CrudRepository<User, String>{

	public User findByUsername(String username);
	
}
