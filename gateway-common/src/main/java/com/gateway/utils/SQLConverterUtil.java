package com.gateway.utils;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

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

    public static String replaceAllPlaceHolder(String sql,Object... params){
        if(StringUtils.isBlank(sql))
            throw new RuntimeException("SQL语句不明确");

        String[] sqlDebris = sql.split("\\?");

        int placeholderCount = sqlDebris.length - 1;
        int paramCount = params!=null?params.length:0;
        if(placeholderCount != paramCount && !sql.contains("?"))
            throw new RuntimeException("参数不匹配:\n"+"sql："+sql+"\nparams："+params.toString());

        StringBuffer sb = new StringBuffer();
        for(int i=0;i<paramCount;i++){
            if(params[i] instanceof String){
                sb.append(sqlDebris[i]).append("'").append(params[i]).append("'");
            }else if(params[i] instanceof Number){
                sb.append(sqlDebris[i]).append(params[i]);
            }else if (params[i] == null) {
                sb.append(sqlDebris[i]).append(params[i]);
            }else{
                sb.append(sqlDebris[i]).append("'").append(params[i]).append("'");
            }
            if(i+1 == paramCount && paramCount+1 == sqlDebris.length){
                sb.append(sqlDebris[i+1]);
            }
        }
        if(StringUtils.isBlank(sb.toString()))
            return sql;
        return sb.toString();
    }

    private static String stringifyParam(Object param) {
        if (param instanceof String || param instanceof java.util.Date) {
            return "'" + param.toString() + "'";
        } else {
            return param.toString();
        }
    }
}
