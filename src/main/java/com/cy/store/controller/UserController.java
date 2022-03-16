package com.cy.store.controller;

import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileSizeException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.controller.ex.FileUploadIOException;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping("get_by_uid")
    public JsonResult<User> getInfo(HttpSession session){
        User uu = userService.findByUid(getUidFromSession(session));
        return new JsonResult<User>(OK,uu);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(String phone, String email, Integer gender, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        User uu = new User();
        uu.setPhone(phone);
        uu.setGender(gender);
        uu.setEmail(email);
        userService.updateInfo(uid,username,uu);
        return new JsonResult<Void>(OK);
    }

    private static final int AVATAR_MAX_SIZE = 10 * 1024 *1024; // 10mb
    private static final List<String> AVATAR_TYPES = new ArrayList<>();
    static{
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file) {
        if (file == null || file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制范围(10MB)");
        }
        if (!AVATAR_TYPES.contains(file.getContentType())){
            throw new FileTypeException("不被允许的文件类型");
        }
        String dirPath = session.getServletContext().getRealPath("upload");
        System.out.println(dirPath);
        File dir = new File(dirPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String filename = "";
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            filename = originalFilename.substring(beginIndex);
        }

        filename = UUID.randomUUID().toString().toUpperCase() + filename;
        File dest = new File(dir, filename);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException("文件上传失败");
        }
        String avatar = "/upload/" + filename;
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateAvatar(uid, avatar, username);
        return new JsonResult<String>(OK, avatar);
    }

}
