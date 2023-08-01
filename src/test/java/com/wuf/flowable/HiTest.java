package com.wuf.flowable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable
 * @Date 2023/8/1 11:38
 */
@SpringBootTest
@Slf4j
public class HiTest {
    @Autowired
    HistoryService historyService;

    @Test
    void test01() {
        List<HistoricProcessInstance> list =
                historyService.createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance hi : list) {
            log.info(" ={},{},{},{},{}, {}", hi.getId(), hi.getName(), hi.getStartActivityId(), hi.getStartTime(
            ), hi.getEndActivityId(), hi.getEndTime());
        }
    }


    @Test
    void test03() {
        List<HistoricActivityInstance> list =
                historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().list();
        for (HistoricActivityInstance hai : list) {
            log.info("流程ID：{}，活动名称：{}，活动ID:{}，活动处理⼈： {}",hai.getProcessInstanceId(),hai.getActivityName(),hai.getActivityId(),hai.getAssignee());
        }
    }


}
