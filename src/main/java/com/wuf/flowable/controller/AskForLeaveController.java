package com.wuf.flowable.controller;

import com.wuf.flowable.pojo.vo.ApproveRejectVO;
import com.wuf.flowable.pojo.vo.AskForLeaveVO;
import com.wuf.flowable.result.SysResult;
import com.wuf.flowable.service.AskForLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.controller
 * @Date 2023/8/4 13:57
 */
@RestController
public class AskForLeaveController {

    @Autowired
    private AskForLeaveService askForLeaveService;

    @PostMapping("/ask_for_leave")
    public SysResult askForLeave(@RequestBody AskForLeaveVO askForLeaveVO) {
        askForLeaveService.askForLeave(askForLeaveVO);
        return SysResult.ok();
    }

    @GetMapping("/search")
    public SysResult searchResult() {
        return SysResult.ok(askForLeaveService.searchResult());
    }

    @GetMapping("/list")
    public SysResult leaveList() {
        return SysResult.ok(askForLeaveService.leaveList());
    }


    @PostMapping("/handler")
    public SysResult askForLeaveHandler(@RequestBody ApproveRejectVO approveRejectVO) {
        askForLeaveService.askForLeaveHandler(approveRejectVO);
        return SysResult.ok();
    }
}
