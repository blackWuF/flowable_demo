package com.wuf.flowable.controller;

import com.wuf.flowable.result.SysResult;
import com.wuf.flowable.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public SysResult getAllRoles() {
        return SysResult.ok("OK",roleService.getAllRoles());
    }
}
