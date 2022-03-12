package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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

public class UserMapperTests {
    /**
     * idea有监测的功能，接口不能直接创建
     *
     */
    @Autowired
    private UserMapper userMapper;

    /** 单元测试方法需要满足：
     * 1.单元测试方法必须被Test修饰
     * 2.返回值必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.类型必须是public
     *
     */

    @Test
    public void insert (){
        User user = new User();
        user.setUsername("Felix");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void updatePasswordByUid(){
        Integer rows = userMapper.updatePasswordByUid(6, "Felix", new Date(), "123123");
        Assert.assertEquals((long) rows, 1);
    }

    @Test
    public void findByUid(){
        Integer uid = 3;
        User uu = userMapper.findByUid(uid);
        Assert.assertEquals("admin01", uu.getUsername());
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(8);
        user.setPhone("10086");
        user.setEmail("shit@gmail.com");
        user.setGender(1);
        user.setModifiedUser("Felix");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);

        Assert.assertEquals((long)rows, 1);
    }


}
