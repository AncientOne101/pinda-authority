package com.itheima.pinda;

import com.itheima.pinda.validator.config.EnableFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
// 一个标记注释，告诉 Spring Cloud 这个应用程序是一个应该向发现服务器注册的服务。
@EnableDiscoveryClient
// 它启用 Hystrix 断路器。
@EnableHystrix
// 告诉 Spring Cloud 扫描 Feign 客户端的包。
@EnableFeignClients(value = {
        "com.itheima.pinda",
})
// 启用 Spring 的注解驱动的事务管理功能的 Spring 注解。
@EnableTransactionManagement
// `Slf4j` 是一个日志框架。
@Slf4j
// 启用表单验证器的自定义注释。
@EnableFormValidator
public class FileServerApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(FileServerApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \t\thttp://{}:{}/doc.html\n\t" +
                        "数据库监控: \t\thttp://{}:{}/druid\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                "127.0.0.1",
                env.getProperty("server.port"));

    }
}