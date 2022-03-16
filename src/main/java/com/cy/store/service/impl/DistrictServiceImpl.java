package com.cy.store.service.impl;

import com.cy.store.entity.District;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/16 19:17
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;


    @Override
    public List<District> getByParent(String parent) {
        List<District> districts = districtMapper.getByParent(parent);
        for (District district : districts) {
            district.setId(null);
            district.setParent(null);
        }
        return districts;
    }
}
