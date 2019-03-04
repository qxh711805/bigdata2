package com.rimi.eurekaclientc.controller;

import com.rimi.eurekaclientc.service.ClientAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private ClientAService clientAService;

    @GetMapping("/hi")
    public String hi(String name){
        return clientAService.hi(name);
    }
}
