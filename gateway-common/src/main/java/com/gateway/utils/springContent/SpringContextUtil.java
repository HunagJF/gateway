package com.gateway.utils.springContent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeansException;

/**
 * SpringContextUtil 是一个实用工具类，用于在整个应用程序中访问 Spring 应用上下文。
 * 它提供了获取 Spring 管理的 bean 的方法。
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    // 保存应用上下文的静态变量
    private static ApplicationContext applicationContext;

    /**
     * 实现 ApplicationContextAware 接口的方法，Spring 在启动时会自动调用这个方法来设置应用上下文。
     * @param applicationContext 传入的应用上下文
     * @throws BeansException 如果在设置应用上下文时发生错误
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 根据类型获取 bean。
     * @param <T> 要获取的 bean 的类型
     * @param requiredType bean 的 Class 对象
     * @return 返回指定类型的 bean 实例
     * @throws IllegalStateException 如果 applicationContext 未设置
     */
    public static <T> T getBean(Class<T> requiredType) {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext 未设置。");
        }
        return applicationContext.getBean(requiredType);
    }

    /**
     * 根据名称获取 bean。
     * @param beanName bean 的名称
     * @return 返回指定名称的 bean 实例
     * @throws IllegalStateException 如果 applicationContext 未设置
     */
    public static Object getBean(String beanName) {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext 未设置。");
        }
        return applicationContext.getBean(beanName);
    }

    /**
     * 根据名称和类型获取 bean。
     * @param <T> 要获取的 bean 的类型
     * @param beanName bean 的名称
     * @param requiredType bean 的 Class 对象
     * @return 返回指定名称和类型的 bean 实例
     * @throws IllegalStateException 如果 applicationContext 未设置
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext 未设置。");
        }
        return applicationContext.getBean(beanName, requiredType);
    }
}
