package com.cy.store.service;

import com.cy.store.entity.User;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/9 15:09
 */
/* 用户模块业务层的接口*/
public interface IUserService {
    void reg (User user);

    User login(String username, String password);

}
