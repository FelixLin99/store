package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private IDistrictService districtService;


    @Value("${project.address.max-count}") // 读取配置文件中的值
    private int maxCount;

    @Override
    public void createAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.getAddressesCntByUid(uid);
        if (count >= maxCount){
            throw new AddressCountLimitException("收货地址超出限制");
        }

        String areaName = districtService.getNameByCode(address.getAreaCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
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

    @Override
    public List<Address> getAddressesByUid(Integer uid) {
        List<Address> addresses = addressMapper.getAddressesByUid(uid);
        for (Address address :addresses){
            //address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return addresses;
    }
    // Transactional：基于Spring JDBC的事务（Transaction）处理，使用事务可以保证一系列的增删改操作，要么全部执行成功，
    // 要么全部执行失败。@Transactional注解可以用来修饰类也可以用来修饰方法。如果添加在业务类之前，则该业务类中的方法均以事务的机制运行，但是一般并不推荐这样处理。
    @Transactional
    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        // 根据参数aid，调用addressMapper中的findByAid()查询收货地址数据
        Address result = addressMapper.findByAid(aid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问的异常");
        }

        // 调用addressMapper的updateNonDefaultByUid()将该用户的所有收货地址全部设置为非默认，并获取返回受影响的行数
        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        // 判断受影响的行数是否小于1(不大于0)
        if (rows < 1) {
            // 是：抛出UpdateException
            throw new UpdateException("设置默认收货地址时出现未知错误[1]");
        }

        // 调用addressMapper的updateDefaultByAid()将指定aid的收货地址设置为默认，并获取返回的受影响的行数
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("设置默认收货地址时出现未知错误[2]");
        }
    }


}
