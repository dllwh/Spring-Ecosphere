package org.dllwh.framework.exception;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.dllwh.common.RestResult;
import org.dllwh.common.util.SystemLogHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow newest starter!
 *
 * @author dllwh
 * @类描述: 自定义全局异常处理器, 异常增强，以JSON的形式返回给客服端
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月12日 下午10:47:57
 * @版本: V1.0.5
 * @since: JDK 1.8
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {
    /**
     * 未知账号异常
     */
    @ExceptionHandler(UnknownAccountException.class)
    public RestResult unknownAccountExceptionHandle(UnknownAccountException uae) {
        log.error(uae.getMessage(), uae);
        SystemLogHelper.expLog(uae);
        return RestResult.error(uae.getMessage());
    }

    /**
     * 不正确的凭据异常
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public RestResult incorrectCredentialsExceptionHandle(IncorrectCredentialsException ice) {
        log.error(ice.getMessage(), ice);
        SystemLogHelper.expLog(ice);
        return RestResult.error(ice.getMessage());
    }

    /**
     * 用户账号被锁异常
     */
    @ExceptionHandler(LockedAccountException.class)
    public RestResult lockedAccountExceptionHandle(LockedAccountException lae) {
        log.error(lae.getMessage(), lae);
        SystemLogHelper.expLog(lae);
        return RestResult.error(lae.getMessage());
    }

    /**
     * 授权异常
     */
    @ExceptionHandler(AuthorizationException.class)
    public RestResult handleAuthorizationExceptionHandle(AuthorizationException ae) {
        log.error(ae.getMessage(), ae);
        SystemLogHelper.expLog(ae);
        return RestResult.error(ae.getMessage());
    }

    /**
     * 账号被禁用
     */
    @ExceptionHandler(DisabledAccountException.class)
    public RestResult disabledAccountExceptionHandle(DisabledAccountException dae) {
        log.error(dae.getMessage(), dae);
        SystemLogHelper.expLog(dae);
        return RestResult.error(dae.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RestResult handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        SystemLogHelper.expLog(e);
        return RestResult.error(405, "不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public RestResult nullPointerExceptionHandler(NullPointerException ex) {
        SystemLogHelper.expLog(ex);
        return RestResult.error(404, "空指针异常");
    }

    /**
     * 未知方法异常
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public RestResult noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        SystemLogHelper.expLog(ex);
        return RestResult.error(404, "未知方法异常");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RestResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        SystemLogHelper.expLog(e);
        return RestResult.error(400, e.getMessage());
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public RestResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        SystemLogHelper.expLog(ex);
        return RestResult.error(1005, ex.getMessage());
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RestResult handleHttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        SystemLogHelper.expLog(e);
        return RestResult.error(400, "缺少请求参数");
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public RestResult server500(Exception e) {
        log.error("运行时异常:", e);
        SystemLogHelper.expLog(e);
        return RestResult.error(500, "运行时异常:" + e.getMessage());
    }

    /**
     * 406错误
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public RestResult request406(Exception e) {
        SystemLogHelper.expLog(e);
        return RestResult.error(406, null);
    }

    /**
     * 非法参数
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException mane) {
        SystemLogHelper.expLog(mane);
        return RestResult.error(mane.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public RestResult runtimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常:", e);
        SystemLogHelper.expLog(e);
        return RestResult.error("运行时异常:" + e.getMessage());
    }

    /**
     * 处理未定义的其他异常信息
     */
    @ExceptionHandler(Exception.class)
    public RestResult exceptionHandler(Exception e) {
        log.error("处理未定义的其他异常信息:", e);
        SystemLogHelper.expLog(e);
        return RestResult.error("运行时异常:" + e.getMessage());
    }
}
