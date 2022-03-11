package com.cy.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/11 20:18
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 检测全局session对象中是否有uid数据，如果有就放行，没有就重定向到登陆页面。
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url+controller：映射）
     * @return 放行则true；拦截则false；
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object uid = request.getSession().getAttribute("uid");
        if (uid == null){
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
