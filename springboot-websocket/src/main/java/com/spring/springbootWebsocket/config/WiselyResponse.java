package com.spring.springbootWebsocket.config;
/**
 * 服务器向浏览器传送消息，用该类进行传送
 * @author dxk
 *
 */
public class WiselyResponse {
	 private String responseMessage;
	    public WiselyResponse(String responseMessage){
	        this.responseMessage = responseMessage;
	    }
	    public String getResponseMessage(){
	        return responseMessage;
	    }
}
