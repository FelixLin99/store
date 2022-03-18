package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/16 16:42
 */
public interface IAddressService {
    void createAddress(Integer uid, String username, Address address);

    List<Address> getAddressesByUid(Integer uid);

    void setDefault(Integer aid, Integer uid, String username);
}
