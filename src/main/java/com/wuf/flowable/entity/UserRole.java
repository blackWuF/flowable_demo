package com.wuf.flowable.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.entity
 * @Date 2023/8/4 10:43
 */
@ApiModel(value="com-wuf-flowable-entity-UserRole")
@Data
public class UserRole implements Serializable {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="")
    private Integer uid;

    @ApiModelProperty(value="")
    private Integer rid;

    private static final long serialVersionUID = 1L;
}