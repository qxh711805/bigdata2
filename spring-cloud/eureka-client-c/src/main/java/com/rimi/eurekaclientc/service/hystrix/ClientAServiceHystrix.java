package com.rimi.eurekaclientc.service.hystrix;

import com.rimi.eurekaclientc.service.ClientAService;

public class ClientAServiceHystrix implements ClientAService {
    @Override
    public String hi(String name) {
        return "请求失败，参数为："+name;
    }
}
