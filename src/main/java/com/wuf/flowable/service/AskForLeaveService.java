package com.wuf.flowable.service;

import com.wuf.flowable.pojo.vo.ApproveRejectVO;
import com.wuf.flowable.pojo.vo.AskForLeaveVO;
import com.wuf.flowable.pojo.vo.HistoryInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.service
 * @Date 2023/8/4 14:01
 */
public interface AskForLeaveService {
    void askForLeave(AskForLeaveVO askForLeaveVO);

    List<HistoryInfo> searchResult();

    List<Map<String, Object>> leaveList();

    void askForLeaveHandler(ApproveRejectVO approveRejectVO);
}
