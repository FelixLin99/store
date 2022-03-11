package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/9 16:35
 */
/* 控制层类的基类*/
public class BaseController {
    public static final int OK = 200;

    // 请求处理的方法，这个方法的返回值就是需要传递给全端的数据
    // 会自动将异常对象传递给此方法的参数列表上
    // 所有ServiceException异常都会被这个拦截，经过这个方法的处理
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        } else if (e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户不存在");
        } else if (e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("密码不正确");
        }
        return result;
    }

    protected final Integer getUidFromSession(HttpSession session){
        return Integer.parseInt(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
