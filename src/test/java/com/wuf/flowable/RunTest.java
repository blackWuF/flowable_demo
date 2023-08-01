package com.wuf.flowable;

import com.wuf.flowable.config.FlowableDeployer;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.ProcessInstance;

import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable
 * @Date 2023/7/28 11:37
 */
@SpringBootTest
@Slf4j
public class RunTest {
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private FlowableDeployer flowableDeployer;
    @Autowired
    private TaskService taskService;

//    private static final Logger logger = LoggerFactory.getLogger(RunTest.class);



    @Test
    void test01() {
        Authentication.setAuthenticatedUserId("wangwu");
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("leave");
        log.info("id:{},activityId:{}",pi.getId(),pi.getActivityId());
    }

    @Test
    void deploy(){
        try {
            flowableDeployer.deploy(null, "leave");
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Test
    void test03() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("lisi").list();
        for (Task task : list) {
            taskService.complete(task.getId());
        }
    }

    @Test
    void test06() {
        runtimeService.deleteProcessInstance("65ab0b38-38f3-11ed-b103-acde48001122", "javaboy想删除了");
    }


}

