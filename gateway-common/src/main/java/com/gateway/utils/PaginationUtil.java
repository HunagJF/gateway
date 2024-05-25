package com.gateway.utils;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

public class PaginationUtil {

    private static final String PAGE = "page";
    private static final String SIZE = "size";

    public static void getPagination(Map<String, Object> param) {
        param.put(PAGE, MapUtils.getInteger(param, PAGE, 1) - 1);
        param.put(SIZE, MapUtils.getInteger(param, SIZE, 1) - 1);
    }

    public static String getPagingConcatenation(String sql, Map<String, Object> param) {
        return sql + " LIMIT " + MapUtils.getInteger(param, SIZE) + " OFFSET " +
                MapUtils.getInteger(param, PAGE) * MapUtils.getInteger(param, SIZE);
    }

}
