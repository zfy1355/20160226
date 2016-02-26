package org.serverMonitor.json;

/**
 * 响应代码枚举定义
 * 
 * @author chaijunkun
 * @since 2014年10月17日
 */
public enum RespCode {

	SUCCESS(0, "成功"), 
	AUTH_FAILURE(100, "鉴权失败"), 
	RUNTIME_ERR(200, "未知错误"), 
	PARAM_INVALID(300, "参数无效"), 
	NO_DATA(400,"未找到数据"), 
	ILLEGAL(500, "参数非法"), 
	PUSH_ERROR(501, "上传失败"), 
	SAVE_ERROR(502, "保存失败");

	/** 响应代码 */
	private int code;

	/** 响应含义 */
	private String meaning;

	private RespCode(int code, String meaning) {
		this.code = code;
		this.meaning = meaning;
	}

	/** 获取对应的响应代码 */
	public int getCode() {
		return this.code;
	}

	/** 获取对应的响应代码含义 */
	public String getMeaning() {
		return this.meaning;
	}

}
