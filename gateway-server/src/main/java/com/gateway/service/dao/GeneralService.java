package com.gateway.service.dao;

import java.util.List;
import java.util.Map;

public interface GeneralService {

    Map<String, Object> query(String sql);

    List<Map<String, Object>> querys(String sql);

    int update(String sql);

    int insert(String sql);

    int delete(String sql);

    String nextVal();
}
