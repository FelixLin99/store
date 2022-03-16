package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/16 16:43
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Value("${project.address.max-count}") // 读取配置文件中的值
    private int maxCount;

    @Override
    public void createAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.getAddressesCntByUid(uid);
        if (count >= maxCount){
            throw new AddressCountLimitException("收货地址超出限制");
        }
        address.setUid(uid);
        address.setModifiedUser(username);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        address.setIsDefault(count == 0? 1 : 0);
        Integer rows = addressMapper.insert(address);
        if (rows != 1){
            throw new InsertException("创建收货地址失败");
        }

    }
}
