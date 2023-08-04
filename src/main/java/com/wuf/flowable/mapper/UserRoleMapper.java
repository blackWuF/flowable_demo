package com.wuf.flowable.mapper;

import com.wuf.flowable.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.mapper
 * @Date 2023/8/4 10:43
 */
@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}