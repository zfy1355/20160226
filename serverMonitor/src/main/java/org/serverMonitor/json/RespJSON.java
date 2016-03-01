package org.serverMonitor.json;

import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 接口返回单个结果的泛型类
 * @author chaijunkun
 * @param <T> 响应消息的result子类型
 */
@JsonPropertyOrder(alphabetic= false)
public class RespJSON<T> {
	
	private int code;

	private boolean success;

	private String message;

	private T result;
	
	public RespJSON(){}
	
	/**
	 * 根据返回代码生成默认返回对象
	 * @param respCode
	 */
	public RespJSON(RespCode respCode){
		this.code= respCode.getCode();
		this.message= respCode.getMeaning();
		if (RespCode.SUCCESS.equals(respCode)){
			this.success = true;
		}else{
			this.success= false;
		}
	}
	
	/**
	 *  无数据返回结果
	 * @param respDataCode
	 */
	public RespJSON(RespDataCode respDataCode){
		this.code = respDataCode.getCode();
		this.message = respDataCode.getMeaning();
		this.success = false;
	}
	
	
	/**
	 * 生成自定义返回对象
	 * @param code 错误代码
	 * @param msg 错误消息
	 */
	public RespJSON(int code, String msg){
		this.code= code;
		this.message= msg;
		if (RespCode.SUCCESS.getCode() == code){
			this.success = true;
		}else{
			this.success= false;
		}
	}
	
	/**
	 * 生成错误消息
	 * @param message 错误消息体
	 */
	public RespJSON(String message){
		this(new Exception(message));
	}
	
	/**
	 * 生成错误消息
	 * @param exception 抛出的异常
	 */
	public RespJSON(Exception exception){
		/*if (exception instanceof AuthException) {
			this.code = ((AuthException)exception).getCode();
			this.message = exception.getMessage();
		} else */if (exception instanceof SQLException) {
			this.code = RespCode.NO_DATA.getCode();
			this.message = RespCode.NO_DATA.toString();
		} else if (exception instanceof IllegalArgumentException){
			this.code = RespCode.ILLEGAL.getCode();
			this.message = RespCode.ILLEGAL.getMeaning();
		}else {
			this.code = RespCode.RUNTIME_ERR.getCode();
			this.message = exception.getMessage();
		}
		this.success = false;
	}

	/**
	 * 生成成功消息
	 * @param result 返回数据
	 */
	public RespJSON(T result){
		this.code= RespCode.SUCCESS.getCode();
		this.message= RespCode.SUCCESS.getMeaning();
		this.success= true;
		this.result= result;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public T getResult() {
		return result;
	}
	
	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
