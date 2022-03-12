package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/9 16:19
 */
// @Controller
@RestController // 等于Controller+ResonseBody的组合
@RequestMapping("users") //表示users类型的请求会被拦截，由这个Controller处理
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("reg") // users下的reg可以拦截到此类方法当中
    // @ResponseBody // 表示此方法的响应结果以json格式进行数据的响应给到前端。
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);

//        try {
//            userService.reg(user);
//            result.setState(200);
//            result.setMessage("用户注册成功");
//        } catch (UsernameDuplicatedException e) {
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        } catch (InsertException e){
//            result.setState(5000);
//            result.setMessage("注册时产生未知异常");
//        }
    }

    @RequestMapping("login")
    public JsonResult<User> login(User user, HttpSession session){
        User u = userService.login(user.getUsername(), user.getPassword());
        session.setAttribute("uid", u.getUid());
        session.setAttribute("username", u.getUsername());
        return new JsonResult<User>(OK,u); //希望把user数据存储下来，作为一个全局变量，
    }

    @RequestMapping("change_password")
    public JsonResult<Void> update(String newPassword, String oldPassword, HttpSession session){
        userService.update(getUidFromSession(session),getUsernameFromSession(session),newPassword,oldPassword);
        return new JsonResult<Void>(OK);
    }

}
