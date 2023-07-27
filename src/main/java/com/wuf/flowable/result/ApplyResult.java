package com.wuf.flowable.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApplyResult<T> {
    @ApiModelProperty("请求id")
    private String requestId;
    @ApiModelProperty("错误信息")
    private String errMsg;
    @ApiModelProperty("返回结果")
    private T data;
    @ApiModelProperty("返回结果code")
    private Long errCode;


}
