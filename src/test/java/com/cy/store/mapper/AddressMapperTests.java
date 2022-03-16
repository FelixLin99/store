package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description: TODO
 * @date 2022/3/16 15:51
 */
// 标注当前的类是一个测试类，打包时自动忽略
@SpringBootTest
// 不写的化单元测试类无法运行
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    public AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(8);
        address.setPhone("32132123");
        address.setName("girl");
        Integer rows = addressMapper.insert(address);
        Assert.assertEquals((long)rows, 1);
    }

    @Test
    public void getAddressesCntByUid(){
        Integer cnt = addressMapper.getAddressesCntByUid(8);
        Assert.assertEquals((long)cnt, 1);
        Integer cnt2 = addressMapper.getAddressesCntByUid(1);
        Assert.assertEquals((long)cnt2, 0);
    }
}
