package com.wuf.flowable.mapper;

import com.wuf.flowable.entity.Role;
import com.wuf.flowable.entity.User;
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
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loadUserByUsername(String username);

    List<Role> getUserRolesByUserId(Integer userId);

    List<User> getAllUsers();   
}