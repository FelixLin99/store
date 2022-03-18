package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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

    @Test
    public void getAddressesByUid(){
        List<Address> addressesByUid = addressMapper.getAddressesByUid(8);
        System.out.println(addressesByUid);
    }

    @Test
    public void updateNonDefaultByUid() {
        Integer uid = 8;
        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateDefaultByAid() {
        Integer aid = 5;
        String modifiedUser = "管理员";
        Date modifiedTime = new Date();
        Integer rows = addressMapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByAid() {
        Integer aid = 5;
        Address result = addressMapper.findByAid(aid);
        System.out.println(result);
    }
}
