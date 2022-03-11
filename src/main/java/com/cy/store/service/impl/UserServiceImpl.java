package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.UserNotFoundException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.print.DocFlavor;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/9 15:10
 */
@Service // Service 注解将当前类的对象交给spring管理，可以自动创建对象并维护
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        User result = userMapper.findByUsername(user.getUsername());
        if (result != null){
            throw new UsernameDuplicatedException("用户名已存在");
        }

        /** 密码加密处理的实现：md5算法的输出： 67DFEFEF-FDEFICNEV-WCEVV123-EQFFEV-231344
         * (串 + passsword + 串) -> 交给md5算法进行加密，连续加载三次
         * 串 专业术语是 盐值， 盐值就是一个字符串，可以通过 UUID.randomUUID()获取
         * (盐值 + passsword + 盐值) -> 交给md5算法进行加密，连续加载三次
         */
        String oldPsw = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String newPsw = getMD5Password(oldPsw, salt);
        user.setPassword(newPsw);
        user.setSalt(salt);

        // 补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        // 注意异常捕获！！！
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            throw new InsertException("在用户注册过程中产生了未知异常");
        }
    }

    @Override
    public User login(String username, String password) {
        //
        User result = userMapper.findByUsername(username);
        if (result == null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户名不存在");
        }
        String md5Password = getMD5Password(password, result.getSalt());
        if (!md5Password.equals(result.getPassword())){
            throw new PasswordNotMatchException("用户密码错误");
        }

        //调优部分，只传递需要的数据
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    /* 定义一个md5算法的加密处理*/
    private String getMD5Password(String password, String salt){
        // md5加密算法的调用
        for (int i = 0; i<3; i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
