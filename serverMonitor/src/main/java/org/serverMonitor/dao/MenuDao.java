package org.serverMonitor.dao;

import org.serverMonitor.entity.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends CrudRepository<Menu, String> {

}
