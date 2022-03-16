package com.cy.store.service;

import com.cy.store.entity.Address;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/16 16:42
 */
public interface IAddressService {
    void createAddress(Integer uid, String username, Address address);
}
