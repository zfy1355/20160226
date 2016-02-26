package org.serverMonitor.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.serverMonitor.shiro.authc.ShiroSecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {
	Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private ShiroSecurityHelper shiroSecurityHelper;
	
	public LoginController(){
		System.out.println("LOGIN Controller");
	}

	@RequestMapping(value="/login")
	public String login(@RequestParam(value = "userName") String userName,
	@RequestParam(value = "password") String password,
	ModelMap model,HttpServletRequest request){
		logger.info("login info: " + userName);
		UsernamePasswordToken token=null;
		try {
			Subject currentUser = SecurityUtils.getSubject();
			logger.info(" isAuthenticated:" + currentUser.isAuthenticated());
			logger.info("isRemembered:" + currentUser.isRemembered());
			//缓存session
			Session session=shiroSecurityHelper.seachCacheDetail(userName);
			
			 token = new UsernamePasswordToken(userName.trim(),
					password.trim(), false, request.getRemoteHost());
			if (session!=null) {
					if (currentUser.getSession().getId().equals(session.getId())) {
						currentUser.login(token);
						return "redirect:/home";
					}else {
						if (currentUser.getSession().getHost().equals(session.getHost())) {
							shiroSecurityHelper.deleteSessionById(session);
							currentUser.login(token);
							return "redirect:/home";
						}else {
							logger.error("此账号正在别的地登录!" + userName);
							throw new ConcurrentAccessException(); //
						}
					}
			}
			currentUser.login(token);
			token.clear();
			logger.info("认证成功，进入系统后台页面!");
			return "redirect:/disk/listDisk.html";
		
		} catch (ConcurrentAccessException e)
		{
			model.put("message", "此账号正在别的地登陆!");
			logger.info("Unknown User!");
			return "login";
		}catch (UnknownAccountException uae) {
			model.put("message", "没有此用户!");
			logger.info("Unknown User!");
			return "login";
		} catch (IncorrectCredentialsException ice) {
			model.put("message", "账号或密码错误!");
			logger.info("Incorrect Password!");
			return "login";
		} catch (LockedAccountException lae) {
			model.put("message", "用户已经被锁定不能登录，请与管理员联系!");
			logger.info("User Locked!");
			return "login";
		} catch (ExcessiveAttemptsException eae) {
			model.put("message", "错误次数过多,请过半小时再试!");
			logger.info("User Locked!");
			return "login";
		} catch (AuthenticationException e) {
			model.put("message", e.getMessage());
			logger.info("Authentication Failed!");
			return "login";
		}
	}

}
