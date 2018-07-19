package com.zcmzjp.wx.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**配置异步任务分发
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-19 16:39
 */
@Configuration
@EnableScheduling
@EnableAsync
public class ExecutorConfig {

    /*@Value("${sms.executor.corePoolSize}")
    private int corePoolSize;

    @Value("${sms.executor.maxPoolSize}")
    private int maxPoolSize;

    @Value("${sms.executor.queueCapacity}")
    private int queueCapacity;*/

    private final static int corePoolSize = 10;

    private final static int maxPoolSize = 10;

    private final static int queueCapacity = 10;


    @Bean(name = "mailAsync")
    public Executor mailAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("MailExecutor-");
        executor.initialize();
        return executor;
    }

}
