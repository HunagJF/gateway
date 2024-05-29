package com.gateway.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象作为 JSON 写入 HttpServletResponse
     *
     * @param response HttpServletResponse 对象
     * @param result   要写入响应的对象
     */
    public static void writeJson(HttpServletResponse response, Object result) {
        writeJson(response, result, HttpServletResponse.SC_OK);
    }

    /**
     * 将对象作为 JSON 写入 HttpServletResponse，并设置 HTTP 状态码
     *
     * @param response   HttpServletResponse 对象
     * @param result     要写入响应的对象
     * @param statusCode HTTP 状态码
     */
    public static void writeJson(HttpServletResponse response, Object result, int statusCode) {
        response.setStatus(statusCode);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name()); // 设置响应编码为 UTF-8
        response.setContentType("application/json"); // 设置响应内容类型为 JSON
        try {
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().write(json);
        } catch (IOException e) {
            // 记录异常日志
            System.err.println("Error writing JSON to response: " + e.getMessage());
            // 处理异常响应
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing JSON response");
            } catch (IOException ex) {
                // 忽略进一步的异常
                System.err.println("Error sending error response: " + ex.getMessage());
            }
        }
    }

    /**
     * 将错误消息作为 JSON 写入 HttpServletResponse，并设置 HTTP 状态码
     *
     * @param response   HttpServletResponse 对象
     * @param errorMessage 错误消息
     * @param statusCode HTTP 状态码
     */
    public static void writeError(HttpServletResponse response, String errorMessage, int statusCode) {
        response.setStatus(statusCode);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name()); // 设置响应编码为 UTF-8
        response.setContentType("application/json"); // 设置响应内容类型为 JSON
        try {
            String json = objectMapper.writeValueAsString(new ErrorResponse(errorMessage));
            response.getWriter().write(json);
        } catch (IOException e) {
            // 记录异常日志
            System.err.println("Error writing error JSON to response: " + e.getMessage());
            // 处理异常响应
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing error response");
            } catch (IOException ex) {
                // 忽略进一步的异常
                System.err.println("Error sending error response: " + ex.getMessage());
            }
        }
    }

    // 用于错误消息的内部类
    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
