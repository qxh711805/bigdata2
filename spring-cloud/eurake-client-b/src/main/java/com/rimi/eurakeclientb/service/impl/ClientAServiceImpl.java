package com.rimi.eurakeclientb.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rimi.eurakeclientb.service.ClientAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientAServiceImpl implements ClientAService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name) {
        return restTemplate.getForObject("http://client-a/hi?name="+name,String.class);
    }

    public String hiError(String name) {
        return "方法 hi 请求失败，参数为："+name;
    }
}
