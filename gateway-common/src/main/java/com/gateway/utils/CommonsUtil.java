package com.gateway.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gateway.constant.JwtClaimsConstant;
import com.gateway.utils.springContent.SpringContextUtil;
import com.gateway.vo.login.UserTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 *
 * @author luohuihua
 */
@Slf4j
public class CommonsUtil {

    private final CacheUtil cacheUtil;

    @Autowired
    public CommonsUtil(CacheUtil cacheUtil) {
        this.cacheUtil = cacheUtil;
    }

    /**
     * 转文本
     *
     * @param data
     * @param defVal
     * @return
     */
    public static String toString(Object data, String defVal) {
        if (data == null) {
            return defVal;
        }
        if (data != null && data instanceof Clob) {
            return disposeClob((Clob) data);
        }
        return data.toString();
    }

    /**
     * 转文本
     *
     * @param data
     * @return
     */
    public static String toString(Object data) {
        return toString(data, "");
    }

    /**
     * 转int
     *
     * @param data
     * @param defVal
     * @return
     */
    public static int toInt(Object data, int defVal) {
        try {
            return Integer.parseInt(data.toString());
        } catch (Exception e) {

        }
        return defVal;
    }

    /**
     * 转int 默认0
     *
     * @param data
     * @return
     */
    public static int toInt(Object data) {
        return toInt(data, 0);
    }

    /**
     * 转long 默认0
     *
     * @param data
     * @param defVal
     * @return
     */
    public static long toLong(Object data, long defVal) {
        try {
            return Long.parseLong(data.toString());
        } catch (Exception e) {

        }
        return defVal;
    }

    public static long toLong(Object data) {
        return toLong(data, 0);
    }

    /**
     * 转double
     *
     * @param data
     * @param defVal
     * @return
     */
    public static double toDouble(Object data, int defVal) {
        try {
            return Double.parseDouble(data.toString());
        } catch (Exception e) {

        }
        return defVal;
    }

    /**
     * 转double 默认0
     *
     * @param data
     * @return
     */
    public static double toDouble(Object data) {
        return toDouble(data, 0);
    }

    /**
     * CLOB转文本
     *
     * @param obj
     * @return
     */
    public static String disposeClob(Object obj) {
        if (obj == null) {
            return "";
        }

        if (obj instanceof Clob) {
            Clob clob = (Clob) obj;
            Reader is = null;
            BufferedReader br = null;
            StringBuffer sb = new StringBuffer();
            try {
                is = clob.getCharacterStream();
                if (is == null) {
                    return "";
                }
                br = new BufferedReader(is);
                String s = br.readLine();
                while (s != null) {
                    sb.append(s);
                    s = br.readLine();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        } else {
            return obj.toString();
        }
    }

    /**
     * 获取用户Token
     * @return
     */
    public static String getUserToken() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                return null;
            }
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String token = CommonsUtil.toString(request.getParameter("Access-Token"));
            if(token.length() <= 0){
                token = CommonsUtil.toString(request.getAttribute("Access-Token"));
            }
            if (token.length() <= 0) {
                token = CommonsUtil.toString(request.getHeader("Authorization"));
            }
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取用户json
     *
     * @return
     */
    public static JSONObject getUserJson() {
        /*String token = getUserToken();
        RedisUtil redisUtil = SpringUtils.getBean(RedisUtil.class);
        Object sysUser = redisUtil.get(token);
        if (sysUser == null) {
            *//*throw new NullUserInfoException();*//*
            return null;
        }
        if (sysUser instanceof Map){
            sysUser = JSONObject.toJSONString(sysUser);
        }
        JSONObject user = JSON.parseObject(sysUser.toString());
        return user;*/
        return null;
    }

    /**
     * 获取用户
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getUser(Class<T> tClass) {
        try {
            String token = getUserToken();
            CacheUtil cacheUtil = SpringContextUtil.getBean(CacheUtil.class);
            T userTypeVO = cacheUtil.getFromCache(JwtClaimsConstant.USER, token, tClass).get();// 假设你有相应的方法实现
            if (userTypeVO == null) {
                return null;
            }
            return userTypeVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
