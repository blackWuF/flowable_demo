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
@ApiModel(value="com-wuf-flowable-entity-Role")
@Data
public class Role implements Serializable {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private String namezh;

    private static final long serialVersionUID = 1L;
}