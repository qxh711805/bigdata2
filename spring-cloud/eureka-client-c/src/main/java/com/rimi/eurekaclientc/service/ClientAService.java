package com.rimi.eurekaclientc.service;

import com.rimi.eurekaclientc.service.hystrix.ClientAServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-a", fallback = ClientAServiceHystrix.class)
public interface ClientAService {

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name") String name);
}
