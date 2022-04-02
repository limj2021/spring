package com.example.spring_client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//swagger--可以将基于SpringMVC和Spring Boot项目的项目代码，自动生成JSON格式的描述文件
//@EnableOpenApi    //百度说是swagger3.00使用的
@Configuration
@EnableSwagger2   //开启Sawgger2--swagger的升级
public class SwaggerConfig {
/*  软件发布的时候，建议注析掉swagger---it内部人员使用的
    localhost:8080/swagger-ui.html
    http://localhost:8080/swagger-ui/index.html#*/
    //配置多个分组
    @Bean
    public Docket docket1() {
        //配置了swagger的docket的bean实例
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    @Bean
    public Docket docket(Environment environment) {

        //显示要显示的swagger环境--识别spring.profiles.active
        Profiles profiles = Profiles.of("dev", "test","pord");
        boolean flag = environment.acceptsProfiles(profiles);
        //获取项目环境 --判断是否处于自己设定的环境当中
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("狂神")
                .enable(flag)
                //是否启动swagger·
                .select()
                //RequestHandlerSelectors配置要扫描的接口的方式
                //basePackage扫描某一个包
                //any扫描全部
                //none都不扫描
                //withClassAnnotation扫描类上的注解
                .apis(RequestHandlerSelectors.basePackage("com.example.spring_client.controller"))
                //将example下面的的文件全部过滤--与上面产生冲突
                //.paths(PathSelectors.ant("/example/**"))
                .build();
    }

    //配置swagger信息-覆盖原来的swagger信息
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("民杰", "地址", "邮箱");

        return new ApiInfo("Api文档",
                "民杰的swagger文档",
                "v1.0",
                "https://blog.kuangstudy.org/licenses/LICENSE-2.0",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }


}
