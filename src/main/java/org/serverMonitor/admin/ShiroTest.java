package org.serverMonitor.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroTest {

    private static Logger logger = LoggerFactory.getLogger(ShiroTest.class);

    public static void main(String[] args) {
        // Using the IniSecurityManagerFactory, which will use the an INI file
        // as the security file.
        Factory<org.apache.shiro.mgt.SecurityManager> factory =  new IniSecurityManagerFactory("classpath:auth.ini");

        // Setting up the SecurityManager...
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject user = SecurityUtils.getSubject();

        logger.info("User is authenticated:  " + user.isAuthenticated());
        UsernamePasswordToken token = new UsernamePasswordToken("root", "secret");
        token.setRememberMe(true);
        try{
	        user.login(token);
	        logger.info("User is authenticated:  " + user.isAuthenticated());
	        logger.info( "User [" + user.getPrincipal() + "] logged in successfully." );
	        if ( user.hasRole( "schwartz" ) ) {
	            logger.info("May the Schwartz be with you!" );
	        } else {
	            logger.info( "Hello, mere mortal." );
	        }
	        if ( user.isPermitted( "lightsaber:weild" ) ) {
	            logger.info("You may use a lightsaber ring.  Use it wisely.");
	        } else {
	            logger.info("Sorry, lightsaber rings are for schwartz masters only.");
	        }
	        user.logout();
	        logger.info("User is authenticated:  " + user.isAuthenticated());
	        
	    } catch ( UnknownAccountException uae ) {
	    	System.out.println(uae);
	    } catch ( IncorrectCredentialsException ice ) { 
	    	System.out.println(ice);
	    } catch ( LockedAccountException lae ) { 
	    	System.out.println(lae);
	    } catch ( ExcessiveAttemptsException eae ) { 
	    	System.out.println(eae);
	    } catch ( AuthenticationException ae ) {
	    	System.out.println(ae);
	        //unexpected error?
	    }
        
       
    }
}
