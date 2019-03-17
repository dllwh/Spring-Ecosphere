package org.dllwh.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Lists;

@Controller
public class WebSocketController {
	@MessageMapping("/gc")
	@SendTo("/topic/gc")
	public List<String> socketGc(String name) {
		List<String> resultList = Lists.newArrayList();
		return resultList;
	}
}