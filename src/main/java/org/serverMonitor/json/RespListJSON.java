package org.serverMonitor.json; 

import java.util.List;

import org.serverMonitor.util.ObjectUtil;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 接口返回多个结果的泛型类
 * @author chaijunkun
 * @since 2014年11月5日 
 * @param <T> 响应消息的列表result子类型
 */
@JsonPropertyOrder(alphabetic= false)
public class RespListJSON<T> extends RespJSON<List<T>> {
	
	private int total;
	
	public RespListJSON(){}
	
	public RespListJSON(RespCode respCode){
		super(respCode);
	}
	
	public RespListJSON(RespDataCode respDataCode){
		super(respDataCode);
	}
	
	public RespListJSON(int code, String msg){
		super(code, msg);
	}
	
	public RespListJSON(Exception exception){
		super(exception);
	}
	
	public RespListJSON(List<T> result){
		super(result);
		this.total = ObjectUtil.objSize(result);
	}
	
	public int getTotal() {
		return total;
	}

	@Override
	public void setResult(List<T> result) {
		super.setResult(result);
		this.total = ObjectUtil.objSize(result);
	}
	
}
