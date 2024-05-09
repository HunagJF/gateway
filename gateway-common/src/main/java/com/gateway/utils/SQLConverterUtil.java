package com.gateway.utils;

public class SQLConverterUtil {

    public static String appendParams(String sql, Object... params) {
        StringBuilder convertedSQL = new StringBuilder(sql);
        int index = 0;
        for (Object param : params) {
            index = convertedSQL.indexOf("?", index);
            if (index == -1) {
                break; // No more ? found
            }
            convertedSQL.replace(index, index + 1, stringifyParam(param));
            index += param.toString().length();
        }
        return convertedSQL.toString();
    }

    private static String stringifyParam(Object param) {
        if (param instanceof String || param instanceof java.util.Date) {
            return "'" + param.toString() + "'";
        } else {
            return param.toString();
        }
    }
}
