package com.guye.sun.managent.config;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;

import com.guye.sun.common.annotation.DefinedLog;
import com.guye.sun.common.exception.DefinedException;
import com.guye.sun.common.utils.IpAddress;
import com.guye.sun.managent.pojo.dto.ManagerDto;
import com.guye.sun.managent.pojo.po.Log;
import com.guye.sun.managent.service.LogService;
import com.guye.sun.managent.utils.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

/**
 * AOP日志记录切点类
 */
@Aspect
@Component
public class DefinedLogAspect {

    /**
     * 本地异常日志记录对象
     */
    private static final Logger logger = LoggerFactory.getLogger(DefinedLogAspect.class);
    private final LogService logsService;
    private final HttpServletRequest request;

    @Autowired
    public DefinedLogAspect(LogService logsService, HttpServletRequest request) {
        this.logsService = logsService;
        this.request = request;
    }


    /**
     * Controller 拦截点,前置通知
     */
    @Pointcut("@annotation(com.guye.sun.common.annotation.DefinedLog)")
    public void beforeController() {
    }

    private String getURI(HttpServletRequest request) throws IllegalStateException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        int start = uri.indexOf(contextPath);
        return uri.substring(start + contextPath.length());
    }

    /**
     * 操作异常记录
     *
     * @param point 切点
     * @param e     异常
     */
    @AfterThrowing(pointcut = "beforeController()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) {
        String ip = null;
        String accountName = null;
        try {
            ip = IpAddress.INTERNET_IP;
        } catch (Exception ee) {
            ip = "无法获取登录用户Ip";
        }
        try {
            Log log = getDefinedLog(point);
            // 登录名
            ManagerDto session = SessionUtil.getSession();
            if (session != null) {
                accountName = session.getAccount();
            } else {
                accountName = "don't username";
            }
            if (log != null) {
                log.setUrl(getURI(request));
                log.setAccount(accountName);
                log.setDuration(new Short("0"));
                log.setIp(ip);
                logsService.insertSelective(log);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     */
    @Around("beforeController()")
    public Object doController(ProceedingJoinPoint point) {
        Object result;
        // 执行方法名
        String accountName;
        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().getSimpleName();
        Log log;
        Long start, time;
        String ip;
        try {
            ip = IpAddress.INTERNET_IP;
        } catch (Exception ee) {
            ip = "无法获取登录用户Ip";
        }
        try {
            // 登录名
            ManagerDto session = SessionUtil.getSession();
            if (session != null) {
                accountName = session.getAccount();
            } else {
                accountName = "don't username";
            }
            log = getDefinedLog(point);
            // 执行方法所消耗的时间
            start = System.currentTimeMillis();
            result = point.proceed();
            time = System.currentTimeMillis() - start;
        } catch (Throwable e) {
            if (e instanceof DefinedException) {
                DefinedException exception = (DefinedException) e;
                throw DefinedException.badRequest(exception.getCode(), exception.getMessage());
            }
            throw new RuntimeException(e);
        }
        // 当前用户
        try {
            if (log != null) {
                logger.debug("=====通知开始=====");
                logger.debug("请求方法:{}.{}()", className, methodName);
                logger.debug("方法描述:{}", JSON.toJSONString(log));
                logger.debug("请求IP:{}", ip);
                logger.debug("=====通知结束=====");
                log.setUrl(getURI(request));
                log.setAccount(accountName);
                log.setDuration(time.shortValue());
                log.setIp(ip);
                logsService.insertSelective(log);
            }
        } catch (Throwable e) {
            // 记录本地异常日志
            logger.error("====通知异常====");
            logger.error("异常信息:", e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception 抛出异常
     */
    private Log getDefinedLog(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName) && method.getParameterTypes().length == arguments.length) {
                DefinedLog bl = method.getAnnotation(DefinedLog.class);
                return new Log(bl.module(), bl.methods(), StringUtils.defaultString(bl.description(), "执行成功!"));
            }
        }
        return null;
    }
}
