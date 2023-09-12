package com.jinx.controller;

import com.jinx.service.SendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SentController {
    @Resource
    private SendService service;
    @GetMapping("/send")
    public void send(){
        service.send();
    }
}
