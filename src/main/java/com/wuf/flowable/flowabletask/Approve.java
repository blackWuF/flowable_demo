package com.wuf.flowable.flowabletask;

import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class Approve implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("申请通过:"+execution.getVariables());
    }
}
