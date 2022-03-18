package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description: TODO
 * @date 2022/3/16 16:52
 */

// 标注当前的类是一个测试类，打包时自动忽略
@SpringBootTest
// 不写的化单元测试类无法运行
@RunWith(SpringRunner.class)

public class AddressServiceTests {
    @Autowired
    private IAddressService iAddressService;

    @Test
    public void createAddress(){
        Address address = new Address();
        address.setName("boy");
        iAddressService.createAddress(8, "admin02", address);
    }
    @Test
    public void setDefault() {
        try {
            Integer aid = 5;
            Integer uid = 8;
            String username = "系统管理员";
            iAddressService.setDefault(aid, uid, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
