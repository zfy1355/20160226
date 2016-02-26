package org.serverMonitor.shiro.realm;

import org.apache.shiro.crypto.hash.Sha512Hash;

public class PasswordUtil {

    private PasswordUtil() {
    }

    public static String encrytPwd(char[] password,String name, String salt) {
//    	Sha512Hash md5Hash = new Sha512Hash(password, salt);
    	Sha512Hash md5Hash=new Sha512Hash(password, name
				+ salt, 99);
        return md5Hash.toString();
    }

}
