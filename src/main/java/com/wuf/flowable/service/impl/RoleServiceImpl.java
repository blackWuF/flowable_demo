package com.wuf.flowable.service.impl;

import com.wuf.flowable.entity.Role;
import com.wuf.flowable.mapper.RoleMapper;
import com.wuf.flowable.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.service.impl
 * @Date 2023/8/4 15:06
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }
}
