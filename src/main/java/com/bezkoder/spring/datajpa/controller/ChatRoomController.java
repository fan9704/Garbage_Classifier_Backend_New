package com.bezkoder.spring.datajpa.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.bezkoder.spring.datajpa.model.ChatClientModel;
import com.bezkoder.spring.datajpa.model.ServerResponseModel;

@Controller
public class ChatRoomController {
    @MessageMapping("/messageControl")
    @SendTo("topic/getResponse")
    public ServerResponseModel said(ChatClientModel responseMessage) throws InterruptedException{
        Thread.sleep(3000);
        return new ServerResponseModel("歡迎來到," + responseMessage.getClientName());
    }
}