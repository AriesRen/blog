package org.renhj.blog.handler;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.renhj.blog.exception.ServiceException;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.enums.ResultCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


//@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class,
            BindException.class, ServletRequestBindingException.class, MethodArgumentNotValidException.class,
            ConstraintViolationException.class})
    public Result handleHttpMessageNotReadableException(Exception e) {
        if (e instanceof BindException) {
            return new Result(ResultCode.BAD_REQUEST.getCode(), ((BindException) e).getAllErrors().get(0).getDefaultMessage());
        }
        return new Result(ResultCode.BAD_REQUEST.getCode(), e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return new Result(ResultCode.METHOD_NOT_SUPPORT, null);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public Result handleUnauthenticatedException(UnauthenticatedException e) {
        return new Result(ResultCode.UNAUTH_ERROR);
    }

    @ExceptionHandler(UnknownAccountException.class)
    public Result unknownAccountException(UnknownAccountException e) {
        return new Result(ResultCode.NOT_EXIST_USER, null);
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result authenticationException(AuthenticationException e) {
        return Result.error(401, e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result authenticationException(AuthorizationException e) {
        return new Result(ResultCode.FORBIDDEN);
    }

    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e) {
        return new Result(ResultCode.SYSTEM_ERROR, null);
    }


    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        return Result.error(500, e.getMessage());
    }

}
