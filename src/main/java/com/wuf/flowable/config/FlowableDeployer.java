package com.wuf.flowable.config;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.config
 * @Date 2023/7/28 16:02
 */
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@Component
public class FlowableDeployer {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 部署流程定义文件到Flowable引擎
     * @param deploymentName 部署名称
     * @param resourceName 流程定义文件名(不包括后缀)
     * @throws Exception
     */
    public void deploy(String deploymentName, String resourceName) throws Exception {
        // 从classpath中加载流程定义文件
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName + ".bpmn20.xml");
        if (inputStream == null) {
            throw new IllegalArgumentException("找不到流程定义文件：" + resourceName);
        }
        // 将流程定义文件部署到Flowable引擎
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment()
                .name(deploymentName)
                .addInputStream(resourceName + ".bpmn20.xml", inputStream);
        if(!StringUtils.isEmpty(deploymentName)){
            deploymentBuilder.name(deploymentName);
        }
        Deployment deployment = deploymentBuilder.deploy();
        System.out.println("流程定义部署成功，部署ID:" + deployment.getId());
    }

    /**
     * 启动流程实例
     * @param processDefinitionKey 流程定义key(在部署时生成)
     * @param variables 流程变量(可选)
     * @return 启动的流程实例ID
     * @throws Exception
     */
    public String startProcess(String processDefinitionKey, Map<String, Object> variables) throws Exception {
        // 根据流程定义key查询流程定义信息
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .singleResult();
        if (processDefinition == null) {
            throw new IllegalArgumentException("找不到流程定义：" + processDefinitionKey);
        }
        // 启动流程实例并传入流程变量(可选)
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, variables).getId();
    }
}

