package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

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

    /**
     * 查询指定uid下的所有收货地址
     * @param uid
     * @return
     */
    List<Address> getAddressesByUid(Integer uid);
    /**
     * 将某用户的所有收货地址设置为非默认地址
     * @param uid 收货地址归属的用户id
     * @return 受影响的行数
     */
    Integer updateNonDefaultByUid(Integer uid);

    /**
     * 将指定的收货地址设置为默认地址
     * @param aid 收货地址id
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据收货地址aid值，查询收货地址详情
     * @param aid 收货地址id
     * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
     */
    Address findByAid(Integer aid);
}
