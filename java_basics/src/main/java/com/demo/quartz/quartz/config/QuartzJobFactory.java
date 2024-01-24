package com.demo.quartz.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

/**
 * Quartz JobFactory 配置, 用于 Spring 注入 Job
 *
 * @author Song gh
 * @version 2024/01/24
 */
@Component
public class QuartzJobFactory extends AdaptableJobFactory {

    @Resource
    private AutowireCapableBeanFactory capableBeanFactory;

    @Nonnull
    @Override
    protected Object createJobInstance(@Nonnull TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}