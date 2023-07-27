package com.wuf.flowable.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;


/**
 * @author shiyj
 */
@Configuration
@EnableKnife4j
@EnableOpenApi
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true", matchIfMissing = true)
public class Knife4jConfiguration {

    private List<RequestParameter> authorizationParameter() {
        RequestParameterBuilder tokenBuilder = new RequestParameterBuilder();
        tokenBuilder
                .name("token")
                .description("令牌")
                .required(false)
                .in(ParameterType.HEADER)
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)))
                .build();
        return Collections.singletonList(tokenBuilder.build());
    }


    @Bean(value = "dataAppraiseApi")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.com.citydo.publicdatautil"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(HttpSession.class)
                .globalRequestParameters(authorizationParameter());
    }

    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title("flowable脚本")
                .description("flowable脚本脚本")
                .termsOfServiceUrl("http://localhost:8002/")
                .version("1.0")
                .build();
    }

}
