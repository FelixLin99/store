package com.cy.store.mapper;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/6 20:52
 */


import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**用户模块的持久层接口*/
// 先规划需要的SQL语句，如用户注册是 INSERT INTO t_user(...) VALUES (...)

public interface UserMapper {
    /**
     *
     * @param user
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     *
     * @param username
     * @return 找到则返回该用户，否则返回Null
     */
    User findByUsername(String username);

    /**
     * @param uid
     * @param modifiedUser
     * @param modifiedTime
     * @param password
     * @return 影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String modifiedUser, Date modifiedTime, String password);

    /**
     *
     * @param uid
     * @return user info
     */
    User findByUid(@Param("uid") Integer uid);

    /**
     * 更新手机，邮箱，性别
     * @param user
     * @return 受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据Uid，更新头像的路径
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(Integer uid, String avatar, String modifiedUser, Date modifiedTime);


}
