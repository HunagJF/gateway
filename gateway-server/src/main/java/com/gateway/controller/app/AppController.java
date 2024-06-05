package com.gateway.controller.app;

import com.gateway.annotation.Logger;
import com.gateway.enums.LogSeverity;
import com.gateway.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/app")
public class AppController {


    @Logger(operSource = "首页",
            severity = LogSeverity.MINOR,
            operName = "模块查询",
            isPersistence = true)
    @PostMapping(value = "/queryHomePageApp")
    public Result queryHomePageApp() {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> objMap = new HashMap<>();
            objMap.put("name","测试"+i);
            list.add(objMap);
        }
        return Result.success(list);
    }
}
