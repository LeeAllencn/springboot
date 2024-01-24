package com.rocky.boot.controller;

import java.security.Principal;

import com.rocky.boot.model.WiselyMessage;
import com.rocky.boot.model.WiselyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author rocky
 */
@Controller
public class WsController {

	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public WiselyResponse say(WiselyMessage message) throws Exception {
		Thread.sleep(3000);
		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}

	/**
	 * //1
	 */
	@Resource
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) { //2
		//3
		if ("wyf".equals(principal.getName())) {
			messagingTemplate.convertAndSendToUser("wisely",
					"/queue/notifications", principal.getName() + "-send:"
							+ msg);
		} else {
			messagingTemplate.convertAndSendToUser("wyf",
					"/queue/notifications", principal.getName() + "-send:"
							+ msg);
		}
	}
}