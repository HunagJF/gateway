package com.gateway.mapper.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GeneralMapper {

    Map<String, Object> query(String sql);

    List<Map<String, Object>> querys(String sql);

    int update(String sql);

    int insert(String sql);

    int delete(String sql);
}
