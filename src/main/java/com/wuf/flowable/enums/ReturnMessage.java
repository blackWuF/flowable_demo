package com.wuf.flowable.enums;

import lombok.Getter;

/**
 * @author gaosl
 * @date 2021/3/16 13:33
 */
@Getter
public enum ReturnMessage {
    SYSTEM_ERROR(500, "系统错误"),
    PARAMS_NOT_EXISTS(12,"参数缺失"),
    USER_NOT_EXISTS(404,"用户不存在"),
    PROVINCE_REQUEST_FAIL(99,"省平台接口请求失败"),
    NOT_DEPARTMENT(13,"请使用部门账户"),
    NOT_DATE(14,"数据不存在"),
    DATA_REPEAT(15,"数据重复"),
    IRS_ERROR(16,"请求IRS接口失败"),
    USER_PERMISSION_NOT_ENOUGH(17,"用户权限不足"),
    ;
    private Integer code;

    private String msg;

    ReturnMessage(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
