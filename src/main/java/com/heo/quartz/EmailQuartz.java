package com.heo.quartz;


import com.heo.common.constant.Constants;
import com.heo.dao.UserMapper;
import com.heo.entity.mapper.ExpressOrder;
import com.heo.service.IExpressOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/10/5
 * @Desc
 */
@Component
@EnableScheduling
public class EmailQuartz {

    @Autowired
    UserMapper userMapper;

    @Autowired
    IExpressOrderService expressOrderService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定时发送统计
     */
    @Scheduled(cron="0 0 21 * * ?")
    public void job() {
        String methodDesc = "统计收入定时任务";
        logger.info(methodDesc + "开始");
        try {
            List<Long> providerList = userMapper.selectUserIdByType(Constants.PROVIDER);
            providerList.stream().forEach(id -> expressOrderService.statisIncome(id, Constants.STATIS_HOUR));
            logger.info(methodDesc + "完成");
        } catch (Exception e) {
            logger.error(methodDesc + "失败", e);
        }

    }

}
