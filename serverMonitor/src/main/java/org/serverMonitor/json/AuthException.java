package org.serverMonitor.json; 

import java.util.EnumSet;

/** 
 * 鉴权异常,当请求API需要授权且鉴权失败时抛出此异常
 * @author chaijunkun
 * @since 2014年10月17日 
 */
public class AuthException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3101412540023542715L;

	/**
	 * 鉴权异常错误代码
	 * @author chaijunkun
	 *
	 */
	public static enum Code {
		//代码值code 需满足 0<=code<=99
		TERMINAL_NOT_FOUND(1, "找不到终端"),
		TERMINAL_IS_ACTIVED_WITH_ANOTHER(2, "该终端已被其他公交或基站绑定"),
		TERMINAL_TYPE_INVALID(3, "终端类型非法"),
		TERMINAL_INVALID(4, "终端非法"),
		UNKNOW(99, "未知鉴权错误");
		
		private int code;
		
		private String meaning;
		
		private Code(int code, String meaning){
			this.code = code;
			this.meaning = meaning;
		}
		
		public int getCode(){
			return this.code;
		}
		
		public String getMeaning(){
			return this.meaning;
		}
		
		/**
		 * 根据枚举类型的错误代码来获取鉴权错误具体类型
		 * @param code
		 * @return
		 */
		public static Code getEnumByCode(int code){
			EnumSet<Code> set = EnumSet.allOf(Code.class);
			for (Code lookup : set) {
				if (lookup.getCode() == code){
					return lookup;
				}
			}
			return Code.UNKNOW;
		}
		
	}

	

}
