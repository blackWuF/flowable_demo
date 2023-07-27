package com.wuf.flowable.controller;

import com.wuf.flowable.result.SysResult;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.controller
 * @Date 2023/7/27 16:34
 */
@RestController
public class ProcessDeployController {
    @Autowired
    RepositoryService repositoryService;
    @PostMapping("/deploy")
    public SysResult deploy(MultipartFile file) throws IOException {
        DeploymentBuilder deploymentBuilder =
                repositoryService.createDeployment()
                        .category("wuf的⼯作流分类")
                        .name("wuf的⼯作流名称")
                        .addInputStream(file.getOriginalFilename(),
                                file.getInputStream())
                        .key("wuf的⼯作流key");
        Deployment deployment = deploymentBuilder
                .deploy();
        return SysResult.ok("部署成功",deployment.getId());
    }
}

