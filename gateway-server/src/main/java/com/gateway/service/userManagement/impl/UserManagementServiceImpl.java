package com.gateway.service.userManagement.impl;

import com.gateway.mapper.dao.GeneralMapper;
import com.gateway.mapper.userManagement.UserManagementMapper;
import com.gateway.result.Result;
import com.gateway.service.userManagement.UserManagementService;
import com.gateway.vo.userManagement.UserManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final GeneralMapper generalMapper;

    private final UserManagementMapper userManagementMapper;

    @Autowired(required = false)
    public UserManagementServiceImpl(GeneralMapper generalMapper, UserManagementMapper userManagementMapper) {
        this.generalMapper = generalMapper;
        this.userManagementMapper = userManagementMapper;
    }

    @Override
    public Result queryUser(Map<String, Object> parem) {
        List<UserManagementVO> userManagementVOS = userManagementMapper.queryUser(parem);
        return Result.success(userManagementVOS, userManagementVOS.size());
    }
}
