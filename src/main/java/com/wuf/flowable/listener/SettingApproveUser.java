package com.wuf.flowable.listener;

import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.engine.delegate.TaskListener;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.listener
 * @Date 2023/8/4 10:41
 */
public class SettingApproveUser implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String approveType = (String) delegateTask.getVariable("approveType");
        if ("by_user".equals(approveType)) {
            delegateTask.setAssignee((String) delegateTask.getVariable("approveUser"));
        } else if ("by_role".equals(approveType)) {
            Object approveRole = delegateTask.getVariable("approveRole");
            delegateTask.addCandidateGroup((String) approveRole);
        }
    }
}
