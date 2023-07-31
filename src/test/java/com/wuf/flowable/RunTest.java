package com.wuf.flowable;

import com.wuf.flowable.config.FlowableDeployer;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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
}

