package com.wuf.flowable.result;

import com.wuf.flowable.enums.ReturnMessage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SysResult<T> {
    private final static int FAIL = 500;
    private final static int SUCCESS = 200;
    private final static String SUCCESS_STRING = "成功";
    private final static Map<String, Object> RESULT = new HashMap();
    public final static boolean TRUE = true;
    public final static boolean FALSE = false;
    @ApiModelProperty("是否正常返回")
    private boolean success;
    @ApiModelProperty("错误信息")
    private Object msg;
    @ApiModelProperty("返回结果")
    private T data;
    @ApiModelProperty("返回结果code")
    private Integer code;

    public static SysResult ok() {
        SysResult sysResult = new SysResult();
        sysResult.setSuccess(TRUE);
        sysResult.setCode(SUCCESS);
        sysResult.setMsg(SUCCESS_STRING);
        sysResult.setData(RESULT);
        return sysResult;
    }

    public static <T> SysResult<T> ok(T data) {
        SysResult<T> sysResult = new SysResult<T>();
        sysResult.setSuccess(TRUE);
        sysResult.setCode(SUCCESS);
        sysResult.setMsg(SUCCESS_STRING);
        sysResult.setData(data);
        return sysResult;
    }

    public static <T> SysResult<T> ok(String messag, T data) {
        SysResult<T> sysResult = new SysResult<T>();
        sysResult.setSuccess(TRUE);
        sysResult.setCode(SUCCESS);
        sysResult.setMsg(messag);
        sysResult.setData(data);
        return sysResult;
    }

    public static SysResult fail(int code, String message) {
        SysResult sysResult = new SysResult();
        sysResult.setSuccess(FALSE);
        sysResult.setCode(code);
        sysResult.setMsg(message);
        sysResult.setData(RESULT);
        return sysResult;
    }

    public static SysResult fail(String message) {
        SysResult sysResult = new SysResult();
        sysResult.setSuccess(FALSE);
        sysResult.setCode(FAIL);
        sysResult.setMsg(message);
        sysResult.setData(RESULT);
        return sysResult;
    }

    public static SysResult fail(ReturnMessage returnMessage){
        SysResult sysResult = new SysResult();
        sysResult.setSuccess(FALSE);
        sysResult.setCode(returnMessage.getCode());
        sysResult.setMsg(returnMessage.getMsg());
        sysResult.setData(RESULT);
        return sysResult;
    }
}
