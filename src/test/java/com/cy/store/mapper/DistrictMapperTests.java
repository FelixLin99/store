package com.cy.store.mapper;

import com.cy.store.entity.District;
import com.cy.store.entity.User;
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
 * @description:
 * @date 2022/3/8 18:45
 */

// 标注当前的类是一个测试类，打包时自动忽略
@SpringBootTest
// 不写的化单元测试类无法运行
@RunWith(SpringRunner.class)

public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void getByParent(){
        List<District> byParent = districtMapper.getByParent("130900");
        System.out.println(byParent.toString());
    }

    @Test
    public void getNameByCode(){
        String nameByCode = districtMapper.getNameByCode("130900");
        System.out.println(nameByCode);
    }


}
