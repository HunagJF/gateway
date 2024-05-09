package com.gateway.service.dao.impl;

import com.gateway.mapper.dao.GeneralMapper;
import com.gateway.service.dao.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GeneralServiceImpl implements GeneralService {

    private final GeneralMapper generalMapper;

    @Autowired
    public GeneralServiceImpl(GeneralMapper generalMapper) {
        this.generalMapper = generalMapper;
    }

    @Override
    public Map<String, Object> query(String sql) {
        return generalMapper.query(sql);
    }

    @Override
    public List<Map<String, Object>> querys(String sql) {
        return generalMapper.querys(sql);
    }

    @Override
    public int update(String sql) {
        return generalMapper.update(sql);
    }

    @Override
    public int insert(String sql) {
        return generalMapper.insert(sql);
    }

    @Override
    public int delete(String sql) {
        return generalMapper.delete(sql);
    }
}
