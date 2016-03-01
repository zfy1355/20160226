package org.serverMonitor.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

@Component
@Aspect
public class LogAspect {
	
	/**自定义切面**/
	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void cutController(){	}
	
	@Around("cutController()")
	public Object log(ProceedingJoinPoint point) {
		System.out.println("------------execute method---------------");
		try {
			Subject currentUser = SecurityUtils.getSubject();
	        if(currentUser.getPrincipal()!=null)
	        	System.out.println("Log-----------userName:"+currentUser.getPrincipals().getPrimaryPrincipal().toString()+"--------------"+point.getSignature().getName());
			return point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
	
/*	@Before("execution(* org.serverMonitor.controller..*(..))")    
    public void before(){  
        System.out.println("切面加强before");  
    }  
	
	
	
	 @After("execution(* org.serverMonitor.controller..*(..))")    
	    public void after(){  
	        System.out.println("切面加强after");  
	    }  
	 */
}
