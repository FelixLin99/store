package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/16 18:52
 */
public interface DistrictMapper {
    List<District> getByParent(String parent);
}
