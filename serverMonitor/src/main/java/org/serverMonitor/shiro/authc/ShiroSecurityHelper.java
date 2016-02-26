package org.serverMonitor.shiro.authc;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.serverMonitor.shiro.cache.RedisCacheManager;
import org.serverMonitor.shiro.realm.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShiroSecurityHelper {
	
	@Autowired
	private  RedisSessionDAO redisSessionDAO;
	@Autowired
	private RedisManager cache;
	@Autowired
	private ShiroAuthorizationHelper shiroAuthorizationHelper;
	@Autowired
	RedisCacheManager redisCacheManager;
	/**
	 * 获得当前用户名
	 * 
	 * @return
	 */
	public static String getCurrentUsername() {
		Subject subject = getSubject();
		PrincipalCollection collection = subject.getPrincipals();
		if (null != collection && !collection.isEmpty()) {
			return (String) collection.iterator().next();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 
	 * @return
	 */
	public static String getSessionId() {
		Session session = getSession();
		if (null == session) {
			return null;
		}
		return getSession().getId().toString();
	}
	
	/**
	 * @param username
	 * @return
	 */
	public  Session getSessionByUsername(String username){
		Collection<Session> sessions = redisSessionDAO.getActiveSessions();
		for(Session session : sessions){
			if(null != session && StringUtils.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)), username)){
				return session;
			}
		}
		return null;
	}
	
	/**踢除用户
	 * @param username
	 */
	public  void kickOutUser(String username){
		Session session = getSessionByUsername(username);
		if(null != session && !StringUtils.equals(String.valueOf(session.getId()), ShiroSecurityHelper.getSessionId())){
			shiroAuthorizationHelper.clearAuthenticationInfo(session.getId());
			redisSessionDAO.delete(session);
		}
	}

/**
 * 删除SESSION
 * @param session
 */
	public void deleteSessionById(	Session session) {
		redisSessionDAO.delete(session);
	}
	
	/**查询缓存中用户的信息
	 * @param username
	 * @return
	 */
	public  Session seachCacheDetail(String username){
		Session session = getSessionByUsername(username);
		return session;
	}
	
	
	/**
	 * 判断当前用户是否已通过认证
	 * @return
	 */
	public static boolean hasAuthenticated() {
		return getSubject().isAuthenticated();
	}

	private static Subject getSubject() {
		return SecurityUtils.getSubject();
	}


}