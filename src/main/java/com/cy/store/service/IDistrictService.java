package com.cy.store.service;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/16 19:16
 */
public interface IDistrictService {
    List<District> getByParent(String parent);

    String getNameByCode(String code);
}
