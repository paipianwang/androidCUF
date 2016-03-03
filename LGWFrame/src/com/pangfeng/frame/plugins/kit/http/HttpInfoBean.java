package com.pangfeng.frame.plugins.kit.http;

import org.apache.http.impl.io.HttpResponseParser;
import org.xutils.http.annotation.HttpResponse;

import com.pangfeng.frame.plugins.kit.http.JsonResponseParser;



/**
 * 信息
 * @author 卢涛
 */
@HttpResponse(parser = JsonResponseParser.class)
public class HttpInfoBean{
	private String success;
	private SimpleInfoEntity result;
	//失败时出现
	private String msg;
	//失败时出现
	private String msgid;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public SimpleInfoEntity getResult() {
		return result;
	}
	public void setResult(SimpleInfoEntity result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
}
