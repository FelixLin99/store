package com.cy.store.service;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/16 19:19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent(){
        List<District> byParent = districtService.getByParent("130800");
        for (District d: byParent){
            System.out.println(d);
        }
    }
}
