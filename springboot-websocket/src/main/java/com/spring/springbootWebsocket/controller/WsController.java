package com.spring.springbootWebsocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springbootWebsocket.config.WiselyMessage;
import com.spring.springbootWebsocket.config.WiselyResponse;
/**
 * (1)@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
 * (2)如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
 * 
 * （1）连接SockJS的endpoint是“endpointWisely”，与后台代码中注册的endpoint要一样。
 * （2）创建STOMP协议的webSocket客户端。
 * （3）连接webSocket的服务端。
 * （4）通过stompClient.subscribe（）订阅服务器的目标是'/topic/getResponse'发送过来的地址，与@SendTo中的地址对应。
 * （5）通过stompClient.send（）向地址为"/welcome"的服务器地址发起请求，与@MessageMapping里的地址对应。
 * @author dxk
 *
 */
@RestController
public class WsController {
	@MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WiselyResponse say(WiselyMessage message) throws Exception {
        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }

}
