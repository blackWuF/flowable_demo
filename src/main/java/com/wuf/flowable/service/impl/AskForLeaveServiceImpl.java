package com.wuf.flowable.service.impl;

import com.wuf.flowable.pojo.vo.ApproveRejectVO;
import com.wuf.flowable.pojo.vo.AskForLeaveVO;
import com.wuf.flowable.pojo.vo.HistoryInfo;
import com.wuf.flowable.service.AskForLeaveService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.service.impl`
 * @Date 2023/8/4 14:01
 */
@Service
public class AskForLeaveServiceImpl implements AskForLeaveService {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;
    @Override
    @Transactional
    public void askForLeave(AskForLeaveVO askForLeaveVO) {
        Map<String, Object> variables = new HashMap<>();
        askForLeaveVO.setName(SecurityContextHolder.getContext().getAuthentication().getName());
        variables.put("name", askForLeaveVO.getName());
        variables.put("days", askForLeaveVO.getDays());
        variables.put("reason", askForLeaveVO.getReason());
        variables.put("approveType", askForLeaveVO.getApproveType());
        variables.put("approveUser", askForLeaveVO.getApproveUser());
        variables.put("approveRole", askForLeaveVO.getApproveRole());
        try {
            runtimeService.startProcessInstanceByKey("holidayRequest", askForLeaveVO.getName(), variables);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("提交申请失败");
        }
    }

    @Override
    public List<HistoryInfo> searchResult() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<HistoryInfo> infos = new ArrayList<>();
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(name)
                .orderByProcessInstanceStartTime().desc().list();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
            HistoryInfo historyInfo = new HistoryInfo();
            historyInfo.setStatus(3);
            Date startTime = historicProcessInstance.getStartTime();
            Date endTime = historicProcessInstance.getEndTime();
            List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(historicProcessInstance.getId())
                    .list();
            System.out.println("historicVariableInstances = " + historicVariableInstances);
            for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
                String variableName = historicVariableInstance.getVariableName();
                Object value = historicVariableInstance.getValue();
                if ("reason".equals(variableName)) {
                    historyInfo.setReason((String) value);
                } else if ("days".equals(variableName)) {
                    historyInfo.setDays(Integer.parseInt(value.toString()));
                } else if ("approved".equals(variableName)) {
                    Boolean v = (Boolean) value;
//                    System.out.println("v = " + v);
                    if (v) {
                        historyInfo.setStatus(1);
                    }else{
                        historyInfo.setStatus(2);
                    }
                } else if ("name".equals(variableName)) {
                    historyInfo.setName((String) value);
                } else if ("approveUser".equals(variableName)) {
                    historyInfo.setApproveUser((String) value);
                }
            }
            historyInfo.setStartTime(startTime);
            historyInfo.setEndTime(endTime);
            infos.add(historyInfo);
        }
        return infos;

    }

    @Override
    public List<Map<String, Object>> leaveList() {
        String identity = SecurityContextHolder.getContext().getAuthentication().getName();
        //找到所有分配给你的任务
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(identity).list();
        //找到所有分配给你所属角色的任务
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            tasks.addAll(taskService.createTaskQuery().taskCandidateGroup(authority.getAuthority()).list());
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            Map<String, Object> variables = taskService.getVariables(task.getId());
            variables.put("id", task.getId());
            list.add(variables);
        }
        return list;
    }

    @Override
    public void askForLeaveHandler(ApproveRejectVO approveRejectVO) {
        try {
            boolean approved = approveRejectVO.getApprove();
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("approved", approved);
            variables.put("approveUser", SecurityContextHolder.getContext().getAuthentication().getName());
            Task task = taskService.createTaskQuery().taskId(approveRejectVO.getTaskId()).singleResult();
            taskService.complete(task.getId(), variables);
            if (approved) {
                //如果是同意，还需要继续走一步
                Task t = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                taskService.complete(t.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("操作失败");
        }

    }
}
