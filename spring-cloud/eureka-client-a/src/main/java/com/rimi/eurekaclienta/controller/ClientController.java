package com.rimi.eurekaclienta.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "客户端",description = "客户端")
@RefreshScope
public class ClientController {

    // 获取配置文件中的信息
    @Value("${server.port}")
    private String port;

    @ApiOperation(value = "测试",notes = "描述")
    @ApiParam(name = "name",value = "张三",defaultValue = "默认值",required = true)
    @GetMapping("/hi")
    public String home(String name) {
        return "hi," + name + ",form port: " + port;
    }


    @Value("${test.version}")
    private String version;

    @GetMapping("/test")
    public String test() {
        return "版本：" + version;
    }
}
