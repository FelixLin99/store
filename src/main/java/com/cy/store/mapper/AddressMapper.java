package com.cy.store.mapper;

import com.cy.store.entity.Address;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/16 15:43
 */
public interface AddressMapper {
    /**
     * 插入收货地址
     * @param address
     * @return
     */
    Integer insert(Address address);

    /**
     * 查询指定uid的收货地址个数
     * @param uid
     * @return
     */
    Integer getAddressesCntByUid(Integer uid);
}
