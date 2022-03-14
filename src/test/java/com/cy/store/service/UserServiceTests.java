package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/8 18:45
 */

// 标注当前的类是一个测试类，打包时自动忽略
@SpringBootTest
// 不写的化单元测试类无法运行
@RunWith(SpringRunner.class)

public class UserServiceTests {
    /**
     * idea有监测的功能，接口不能直接创建
     *
     */
    @Autowired
    private IUserService userService;

    /** 单元测试方法需要满足：
     * 1.单元测试方法必须被Test修饰
     * 2.返回值必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.类型必须是public
     *
     */

    @Test
    public void reg (){
        try {
            User user = new User();
            user.setUsername("admin02");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User admin04 = userService.login("admin02", "123");
        System.out.println(admin04);
    }

    @Test
    public void update(){
        userService.update(8, "admin02", "321", "123");
    }

    @Test
    public void findByUid(){
        User uu = userService.findByUid(8);
        Assert.assertEquals(uu.getUsername(), "admin02");
    }

    @Test
    public void updateInfo(){
        User user = new User();
        user.setPhone("66666");
        user.setEmail("13123@1233");
        user.setGender(0);
        userService.updateInfo(8,"admin02",user);
    }

    @Test
    public void updateAvatar(){
        userService.updateAvatar(8,"/test/1.jb", "admin02");
    }

}
