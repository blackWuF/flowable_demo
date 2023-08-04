package com.wuf.flowable.mapper;

import com.wuf.flowable.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.mapper
 * @Date 2023/8/4 10:43
 */
@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getAllRoles();
}