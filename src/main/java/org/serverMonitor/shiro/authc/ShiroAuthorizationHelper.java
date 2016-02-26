package org.serverMonitor.shiro.authc;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.serverMonitor.shiro.cache.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroAuthorizationHelper {

	 /** 
     *  
     */  
    private  RedisCacheManager cacheManager;  
      
    public RedisCacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(RedisCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	private static Logger log = LoggerFactory.getLogger(ShiroAuthorizationHelper.class);  
      
    private  static String keyPrefix = "shiro_redis_cache:";  
    /** 
     * 清除用户的授权信息 
     * @param username 
     */  
    public  void clearAuthorizationInfo(String username){  
        if(log.isDebugEnabled()){  
            log.debug("clear the " + username + " authorizationInfo");  
        }  
        //ShiroCasRealm.authorizationCache 为shiro自义cache名(shiroCasRealm为我们定义的reaml类的类名)  
     //   Cache<Object, Object> cache = cacheManager.getCache(keyPrefix+".authorizationCache");  
        Cache<Object, Object> cache = cacheManager.getCache(keyPrefix+username);  
        cache.remove(username);  
    }  
      
    /** 
     * 清除当前用户的授权信息 
     */  
    public  void clearAuthorizationInfo(){  
        if(SecurityUtils.getSubject().isAuthenticated()){  
            clearAuthorizationInfo(ShiroSecurityHelper.getCurrentUsername());  
        }  
    }  
      
    /**清除session(认证信息) 
     * @param JSESSIONID 
     */  
    public  void clearAuthenticationInfo(Serializable JSESSIONID){  
        if(log.isDebugEnabled()){  
            log.debug("clear the session " + JSESSIONID);  
        }  
        Cache<Object, Object> cache = cacheManager.getCache(keyPrefix+JSESSIONID);  
        cache.remove(keyPrefix+JSESSIONID);  
    }  
  

}
