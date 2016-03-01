package org.serverMonitor.json;

/**
 *  与数据有关的枚举定义
 * @author liuyu
 *
 * @since 2014年11月14日
 */
public enum RespDataCode {
	
	NO_CITY_DATA(411, "城市列表为空"),
	NO_STATION_DATA(412, "场站列表为空"),
	NO_SEL_CITY_OR_STATION(413, "未选择城市或场站"),
	NOT_NULL_CITY(414, "请填写城市相关信息"),
	NOT_NULL_STATION(415, "请填写场站相关信息"),
	NOT_NULL_APP_KEY(416, "请填写APP KEY"),
	NOT_NULL_APP_SECRET(417, "请填写APP SECRET"),
	NOT_NULL_VERIFYCODE(418, "请填写校验码"),
	ERROR_VERIFYCODE(419, "校验未通过,请检查输入是否正确"),
	ERROR_ACTIVE_STATION(420, "场站激活错误"),
	NOT_NUL_DEVICECODE(421, "场站mac地址不识别"),
	NOT_NUL_BUSLINE(421, "公交线路列表为空");

	
	/** 响应代码 */
	private int code;
	
	/** 响应含义 */
	private String meaning;
	
	private RespDataCode(int code, String meaning){
		this.code = code;
		this.meaning = meaning;
	}
	
	/** 获取对应的响应代码 */
	public int getCode(){
		return this.code;
	}
	
	/** 获取对应的响应代码含义 */
	public String getMeaning(){
		return this.meaning;
	}
	
}
