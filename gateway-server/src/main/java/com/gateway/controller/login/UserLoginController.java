package com.gateway.controller.login;

import com.gateway.dto.login.UserTypeDTO;
import com.gateway.annotation.Logger;
import com.gateway.enums.LogSeverity;
import com.gateway.result.Result;
import com.gateway.service.login.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class UserLoginController {

    private final UserLoginService userLoginService;

    @Autowired
    public UserLoginController( UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    /**
     * 用户登录后使用AOP生成JWT令牌
     * 路径：com.gateway.aspect.login.UserLoginAspect
     * @param userTypeDTO
     * @param response
     * @return
     */
    @PostMapping(value = "/login")
    @Logger(operSource = "用户登录",
            severity = LogSeverity.MINOR,
            operName = "记录登录信息",
            isPersistence = true)
    public Result login(@RequestBody UserTypeDTO userTypeDTO, HttpServletResponse response) {
        return userLoginService.queryName(userTypeDTO);
    }

    @PostMapping(value = "/loginOut")
    @Logger(operSource = "用户退出",
            severity = LogSeverity.MINOR,
            operName = "记录退出信息",
            isPersistence = true)
    public Result loginOut(){
        return userLoginService.loginOut();
    }

    @PostMapping(value = "/queryLoginUser")
    @Logger(operSource = "用户管理",
            severity = LogSeverity.MINOR,
            operName = "查询",
            isPersistence = true)
    public Result queryLoginUser(@RequestBody Map<String, Object> parem) {
        return userLoginService.queryLoginUser(parem);
    }

    @Logger(operSource = "用户管理",
            severity = LogSeverity.MINOR,
            operName = "添加或编辑",
            isPersistence = true)
    @PostMapping(value = "/saveOrUpdateLogin")
    public Result saveOrUpdateLogin(@RequestBody Map<String, Object> parem) {
        return userLoginService.saveOrUpdateLogin(parem);
    }

    @Logger(operSource = "用户管理",
            severity = LogSeverity.MINOR,
            operName = "删除",
            isPersistence = true)
    @PostMapping(value = "/deleteByIds")
    public Result deleteByIds(@RequestBody List<Map<String, Object>> parems) {
        return userLoginService.deleteByIds(parems);
    }

    @Logger(operSource = "用户登录",
            severity = LogSeverity.MINOR,
            operName = "校验",
            isPersistence = true)
    @PostMapping(value = "/loginVerify")
    public Result loginVerify() {
        return Result.success();
    }
}
