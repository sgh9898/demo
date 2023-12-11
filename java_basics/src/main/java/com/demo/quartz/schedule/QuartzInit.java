package com.demo.quartz.schedule;

import com.demo.quartz.service.QuartzJobService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Quartz 定时任务初始化(新任务需要初始化至数据库才会激活)
 * <br> 新任务激活方法: 1. 在 {@link #autoInitJob} 配置
 * <br>               2. 使用 {@link QuartzJobService} 手动添加)
 *
 * @author Song gh on 2023/12/11.
 */
@Service
class QuartzInit {

    @Resource
    private QuartzJobService quartzJobService;

    /**
     * 初始化指定的定时任务, 任务名/组名可以随便取
     *
     * @see QuartzJobService#upsertJob
     */
    @PostConstruct
    private void autoInitJob() {
        quartzJobService.upsertJob(QuartzTaskTemplate.class.getName(), "complaints_phone", "xty_complaints", "0/5 * * * * ?", null);
    }
}
