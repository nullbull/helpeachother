package com.heo.mq;

import com.alibaba.fastjson.JSON;
import com.heo.entity.dto.ExpressMessageDTO;
import com.heo.service.IExpressOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auth justinniu
 * @Date 2018/11/3
 * @Desc
 */
@Component
public class RabbitConsumer {
    @Autowired
    IExpressOrderService expressOrderService;

    private final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

    /**
     * 监听器监听 cteate.a的消息
     * @param s
     */
    @RabbitListener(queues = "create.a")
    public void doCreateExpressOrder(String s) {
        String methodDesc = "真正创建ExpressOrder";
        logger.info(methodDesc + "开始>>>>>>>>>>>>>>>>s:{}", s);
        try {
            ExpressMessageDTO dto = (ExpressMessageDTO) JSON.parse(s);
            expressOrderService.doCreateExpressOrder(dto);
            logger.info(methodDesc + "完成");
        } catch (Exception e) {
            logger.info(methodDesc + "失败", e);
        }

    }
}
