package com.wuf.flowable.controller;

import com.wuf.flowable.result.SysResult;
import com.wuf.flowable.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.controller
 * @Date 2023/7/26 17:58
 */

@RestController
public class UserInfoController {

    @Autowired
    MyUserDetailsService userDetailsService;

    @GetMapping("/users")
    public SysResult getUsers() {
        return SysResult.ok("OK",userDetailsService.getAllUsers());
    }
}
